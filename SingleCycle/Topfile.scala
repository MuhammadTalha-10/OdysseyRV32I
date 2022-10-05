package SingleCycle

import chisel3._
import chisel3.util._

class Top_file extends Module{
    val io = IO(new Bundle{
        val Instruction = Output(UInt(32.W))
        
        
    })
    val ALU_m = Module(new ALU)
    val Alu_control = Module(new Alu_cntrl)
    val Branch_control = Module (new BranchControl)
    val ControlUnit = Module(new Control_Unit)
    val data_memory = Module(new data_mem)
    val Instruction_Memory = Module(new instr_mem)
    val Jal_R = Module(new jalr)
    val Program_Counter = Module(new ProgCounter)
    val RegisterFile = Module(new Reg_file)
    val Immediate_Gen =  Module(new ImmdValGen)

    io.Instruction := Instruction_Memory.io.instrout
    Instruction_Memory.io.instaddr := Program_Counter.io.pc(21,2)
    ControlUnit.io.opcode := io.Instruction(6,0)
    RegisterFile.io.rs1_addr := io.Instruction(19,15)
    RegisterFile.io.rs2_addr := io.Instruction(24,20)
    RegisterFile.io.rd_addr := io.Instruction(11,7)
    Immediate_Gen.io.PC_IMM := Program_Counter.io.pc 
    io.Instruction := Immediate_Gen.io.instr
    io.Instruction := Alu_control.io.func3(12,4)
    io.Instruction := Alu_control.io.func7(30)
    val IMM_MUX = MuxCase(0.U,Array(
        (ControlUnit.io.extend_sel === "b00".U) -> Immediate_Gen.io.I_type,
        (ControlUnit.io.extend_sel === "b01".U) -> Immediate_Gen.io.S_type,
        (ControlUnit.io.extend_sel === "b10".U) -> Immediate_Gen.io.U_type,
        (ControlUnit.io.extend_sel === "b11".U) -> DontCare
    

    ))
    
    val rs2_mux = Mux(ControlUnit.io.oprand_B_sel.asBool(),IMM_MUX,RegisterFile.io.rs2_data)
    val rs1_mux = MuxCase(0.S,Array(
        (ControlUnit.io.oprand_A_sel === "b00".U) -> RegisterFile.io.rs1_data,
        (ControlUnit.io.oprand_A_sel === "b01".U) -> Program_Counter.io.pc4,
        (ControlUnit.io.oprand_A_sel === "b10".U) -> Program_Counter.io.pc,
        (ControlUnit.io.oprand_A_sel === "b11".U) -> RegisterFile.io.rs1_data
    )) 
   
    rs1_mux := ALU_m.io.in_A
    rs2_mux := ALU_m.io.in_B 
    
    Alu_control.io.out := ALU_m.io.alu_Op
    Alu_control.io.out := data_memory.io.dataaddr(9,2)
    RegisterFile.io.rs2_data := data_memory.io.datain 
    ControlUnit.io.memwrite := data_memory.io.memwr
    ControlUnit.io.memread := data_memory.io.memrd
    
    val Datamem_Mux = Mux(ControlUnit.io.memtoreg.asBool(),data_memory.io.dataout,RegisterFile.io.rs2_data)
    
    RegisterFile.io.writeData := Datamem_Mux
    Branch_control.io.arg_x := RegisterFile.io.rs1_data
    Branch_control.io.arg_y := RegisterFile.io.rs2_data
    Branch_control.io.alu_op := Alu_control.io.aluOP

    

    val Branch_AND = Alu_control.io.aluOP & ControlUnit.io.branch

    Jal_R.io.rs1 := IMM_MUX
    Jal_R.io.imm := RegisterFile.io.rs1_data

    val pc4_MUX1 = Mux(Branch_AND.asBool(),Immediate_Gen.io.SB_type,Program_Counter.io.pc4)
    
    val pc4_MUX2 = MuxCase(0.U,Array(
        (ControlUnit.io.Next_pc_sel === "b00".U) -> Program_Counter.io.pc4,
        (ControlUnit.io.Next_pc_sel === "b01".U) -> pc4_MUX1,
        (ControlUnit.io.Next_pc_sel === "b10".U) -> Immediate_Gen.io.UJ_type,
        (ControlUnit.io.Next_pc_sel === "b11".U) -> Jal_R.io.pc_count
    ))
    
}
    
    
    

