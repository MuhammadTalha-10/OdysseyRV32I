package SingleCycle

import chisel3._
import chisel3.util._

class instr_mem extends Module{
    val io = IO(new Bundle{
        val instaddr = Input(UInt(32.W))
        val instrout = Output(UInt(32.W))
    })
    val instrmem = Mem(1024,UInt(32.W))
    io.instrout := instrmem.read(io.instaddr)
}