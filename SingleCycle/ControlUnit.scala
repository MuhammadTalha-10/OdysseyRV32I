package SingleCycle
import chisel3._

class Control_Unit extends Module {
    val io = IO(new Bundle{
        val opcode =Input(UInt(7.W))
        val memwrite = Output(UInt(1.W))
        val branch = Output(UInt(1.W))
        val memread =  Output(UInt(1.W))
        val regwrite =  Output(UInt(1.W))
        val memtoreg =  Output(UInt(1.W))
        val Alu_opr = Output(UInt(3.W))
        val oprand_A_sel = Output(UInt(2.W))
        val oprand_B_sel = Output(UInt(1.W))
        val extend_sel = Output(UInt(2.W))
        val Next_pc_sel = Output(UInt(2.W))
})
    when(io.opcode === "b0110011".U){ //R-Type
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b1".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b000".U
        io.oprand_A_sel := "b00".U
        io.oprand_B_sel := "b0".U
        io.extend_sel := "b00".U
        io.Next_pc_sel := "b00".U
    }
    when(io.opcode === "b0100011".U){ //S-Type
        io.memwrite := "b1".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b0".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b101".U
        io.oprand_A_sel := "b00".U
        io.oprand_B_sel := "b1".U
        io.extend_sel := "b10".U
        io.Next_pc_sel := "b00".U
    }
    when(io.opcode === "b0110111".U){ //lui-Type
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b1".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b110".U
        io.oprand_A_sel := "b11".U
        io.oprand_B_sel := "b1".U
        io.extend_sel := "b01".U
        io.Next_pc_sel := "b00".U
    }
    when(io.opcode === "b0010011".U){ //I-Type
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b1".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b001".U
        io.oprand_A_sel := "b00".U
        io.oprand_B_sel := "b1".U
        io.extend_sel := "b00".U
        io.Next_pc_sel := "b00".U
    }
    when(io.opcode === "b1100111".U){ //Jalr
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b1".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b011".U
        io.oprand_A_sel := "b00".U
        io.oprand_B_sel := "b1".U
        io.extend_sel := "b10".U
        io.Next_pc_sel := "b00".U
    }
    when(io.opcode === "b1101111".U){//jal
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b1".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b011".U
        io.oprand_A_sel := "b10".U
        io.oprand_B_sel := "b0".U
        io.extend_sel := "b00".U
        io.Next_pc_sel := "b10".U
    }
    when(io.opcode === "b1100011".U){ //SB-Type
        io.memwrite := "b0".U
        io.branch := "b1".U
        io.memread := "b0".U
        io. regwrite := "b0".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b010".U
        io.oprand_A_sel := "b00".U
        io.oprand_B_sel := "b0".U
        io.extend_sel := "b00".U
        io.Next_pc_sel := "b01".U
    }
    when(io.opcode === "b0010111".U){//auipc
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b0".U
        io. regwrite := "b1".U
        io.memtoreg := "b0".U
        io.Alu_opr := "b111".U
        io.oprand_A_sel := "b10".U
        io.oprand_B_sel := "b1".U
        io.extend_sel := "b10".U
        io.Next_pc_sel := "b00".U
    }
    when(io.opcode === "b0000011".U){ //lw
        io.memwrite := "b0".U
        io.branch := "b0".U
        io.memread := "b1".U
        io. regwrite := "b1".U
        io.memtoreg := "b1".U
        io.Alu_opr := "b100".U
        io.oprand_A_sel := "b00".U
        io.oprand_B_sel := "b1".U
        io.extend_sel := "b00".U
        io.Next_pc_sel := "b00".U
    }
    }
    

