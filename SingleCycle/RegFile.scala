package SingleCycle
import chisel3._

class Reg_file extends Module{
    val io = IO(new Bundle{
        val writeData = Input(SInt(32.W))
        val rs1_addr = Input(UInt(5.W))
        val rs2_addr = Input(UInt(5.W))
        val rd_addr =  Input(UInt(5.W))
        val rs1_data = Output(SInt(32.W))
        val rs2_data = Output(SInt(32.W))
    })
    val register = RegInit(VecInit(Seq.fill(32)(0.S(32.W))))
    io.rs1_data := register(io.rs1_addr)
    io.rs2_data := register(io.rs2_addr)
    when(io.rd_addr === "b00000".U){
        register(io.rd_addr) := 0.S
    }.otherwise{
        register(io.rd_addr) := io.writeData
    } 
}