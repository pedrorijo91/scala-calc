package com.pt.pedrorijo91.calc

import scala.io.StdIn

/**
 * Created by pedrorijo on 16/12/14.
 */
object Calc extends Arith {

  def main(args: Array[String]) : Unit = askInput

  private[this] def askInput : Unit = {
    println("Insert expression: (insert 'Q' for quit the program)")

    val input = StdIn.readLine()

    input match {
      case "Q" | "q" => println("Quitting scala calculator...");
       case _ => {
         val parseResult: Calc.ParseResult[Double] = parseAll(expr, input)

          parseResult match {
           case Success(result, _)  => println("Result: " + result)
           case Error(msg,next) => println("Error: " + msg + " @ " + next.source)
           case Failure(msg,next)  => println("Failure: " + msg + " @ " + next.source)
          }

         askInput
       }
    }
  }

}
