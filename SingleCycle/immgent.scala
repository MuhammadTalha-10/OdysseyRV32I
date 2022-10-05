package SingleCycle
import chisel3._
import chisel3.util._

       


class ImmdValGen extends Module {
    val io = IO (new Bundle{
        val instr = Input (UInt(7.W))
        val S_type = Output (SInt(32.W))
        val I_type = Output (SInt(32.W))
        val U_type = Output (SInt(32.W))
        val UJ_type = Output (SInt(32.W))
        val SB_type = Output (SInt(32.W))
        val PC_IMM = Input(UInt(32.W))
    })
    
    switch(io.instr){
        is("b0010011".U){
            val i = Cat(Fill(20,io.instr(31)),io.instr(31,20))
            io.I_type := i.asSInt()
            io.S_type := 0.S
            io.U_type := 0.S
            io.UJ_type := 0.S
            io.SB_type := 0.S
            
        }
        is("b0100011".U){
            val s = Cat(Fill(20,io.instr(31)),io.instr(31,25),io.instr(11,7))
            io.S_type := s.asSInt()
            io.I_type := 0.S
            io.U_type := 0.S
            io.UJ_type := 0.S
            io.SB_type := 0.S
        }
        is("b1100011".U){
            val SB = Cat(Fill(19,io.instr(31)),io.instr(7),io.instr(25,30),io.instr(31),io.instr(8,11)) + io.PC_IMM
            io.SB_type := SB.asSInt()
            io.S_type := 0.S
            io.I_type := 0.S
            io.U_type := 0.S
            io.UJ_type := 0.S
        }
        is("b0010111".U){
            val u =Cat(Fill(12, io.instr(31)), io.instr(31, 12))
            io.U_type  := u.asSInt()
            io.S_type := 0.S
            io.I_type := 0.S
            io.UJ_type := 0.S
            io.SB_type := 0.S
        }
        is("b1101111".U){
            val UJ = Cat(Fill((11), io.instr(31)), io.instr(31), io.instr(19, 12), io.instr(20), io.instr(31, 21))
            val uj1 = UJ + io.PC_IMM
            io.UJ_type := uj1.asSInt()
            io.S_type := 0.S
            io.SB_type := 0.S
            io.I_type := 0.S
            io.U_type := 0.S
        }
}
}




