package SingleCycle

import chisel3._
import chisel3.util._

class data_mem extends Module{
    val io = IO(new Bundle{
        val dataaddr = Input(UInt(32.W))
        val datain = Input(UInt(32.W))
        val dataout = Output(UInt(32.W))
        val memwr = Input(Bool())
        val memrd = Input(Bool())
    })
    val datamem = Mem(1024,UInt(32.W))
    
    when (io.memrd === 0.B & io.memwr === 1.B){
        datamem.write(io.dataaddr,io.datain)
        io.dataout := 0.U
    }.otherwise{

        io.dataout := datamem.read(io.dataaddr)
        
    }
}