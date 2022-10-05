package SingleCycle
import chisel3._

class ProgCounter extends Module{
    val io = IO(new Bundle{
        val in = Input(SInt(32.W))
        val pc = Output(SInt(32.W))
        val pc4 = Output(SInt(32.W))
        
        
    })
    val PC = RegInit(0.S(32.W))
    PC := io.in
    io.pc := PC
    io.pc4 := PC + 4.S 
}

