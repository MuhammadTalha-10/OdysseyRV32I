package SingleCycle
import chisel3. _
import chisel3.util . _
class LM_IO_Interface_BranchControl extends Bundle {
    val alu_op = Input(UInt(5.W))
    val arg_x = Input(UInt(32.W))
    val arg_y = Input(UInt(32.W))
    val br_taken = Output(Bool())
    val out = Output(Bool())
}
class BranchControl extends Module {
    val io = IO (new LM_IO_Interface_BranchControl)
       
    io.out := 0.B
    switch(io.alu_op){
        is("b10000".U){
            io.out := io.arg_x === io.arg_y
            }
        is("b10001".U){
            io.out := io.arg_x =/= io.arg_y
            }
        is("b10010".U){
            io.out := io.arg_x < io.arg_y
            }
        is("b10011".U){
            io.out := io.arg_x >= io.arg_y
            }
    }
    io.br_taken := io.out 
}
        
        
        
        
        
        
        
        
        
        
    
