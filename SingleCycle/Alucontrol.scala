package SingleCycle
import chisel3._
import chisel3.util._

class Alu_cntrl extends Module{
    val io = IO(new Bundle{
        val aluOP = Input(UInt(3.W))
        val func7 = Input(UInt(7.W))
        val func3 =  Input(UInt(3.W))
        val out = Output(UInt(11.W))
    })
    when(io.aluOP === "b000".U){ // R-Type
        io.out := Cat("b0".U,io.func7,io.func3)
    }.elsewhen(io.aluOP === "b001".U){  //I-Type
        io.out :=  Cat("b00000000".U,io.func3)
    }.elsewhen(io.aluOP === "b101".U){ //S-Type
        io.out := "b00000000000".U
    }.elsewhen(io.aluOP === "b010".U){ // SB-Type
        io.out := Cat("b10".U,"b000000".U,io.func3)
    }.elsewhen(io.aluOP === "b110".U){ // lui
        io.out := "b00000000000".U
    }.elsewhen(io.aluOP === "b011".U){ // UJ-Type
        io.out := "b11111111111".U
    }.elsewhen(io.aluOP === "b111".U){ // auipc
        io.out := "b00000000000".U 
    }.elsewhen(io.aluOP === "b100".U){ //lw
        io.out := "b00000000000".U
    }.otherwise{
        io.out := DontCare
    }
}