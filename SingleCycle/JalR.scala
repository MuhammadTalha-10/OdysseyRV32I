package SingleCycle

import chisel3._
import chisel3.util._
class jalr extends Module{
    val io =  IO(new Bundle{
        val rs1 = Input(UInt(32.W))
        val imm = Input(UInt(32.W))
        val pc_count = Output(UInt(32.W))
    })
    val adder = WireInit(io.rs1 + io.imm)
    io.pc_count := adder & "b11111111111111111111111111111110".U
}

