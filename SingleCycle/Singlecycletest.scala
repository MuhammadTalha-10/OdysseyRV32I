package SingleCycle

import chisel3._
import org.scalatest._
import chiseltest._

class SingleCycle_test extends FreeSpec with ChiselScalatestTester{
    "single cycle test" in{
        test(new Top_file()){
            C =>
            C.clock.step(100)
        }
    }
}