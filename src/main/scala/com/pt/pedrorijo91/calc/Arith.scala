package com.pt.pedrorijo91.calc

import scala.math.pow
import scala.util.parsing.combinator.RegexParsers
/**
 * Created by pedrorijo on 26/12/14.
 */
class Arith extends RegexParsers {

  def number: Parser[Double] = """-?\d+(\.\d*)?""".r ^^ { _.toDouble }

  def factor: Parser[Double] = number | "(" ~> expr <~ ")"

  def term  : Parser[Double] = factor ~ rep( "*" ~ factor | "/" ~ factor) ^^ {
    case number ~ list => list.foldLeft(number) {
      case (x, "*" ~ y) => x * y
      case (x, "/" ~ y) => x / y
    }
  }

  def expr  : Parser[Double] = {
    term ~ "**" ~ term ^^ {
      case x ~ "**" ~ y => pow(x, y)
    } |
     term ~ rep("+" ~ term | "-" ~ term) ^^ {
      case number ~ list => list.foldLeft(number) {
        case (x, "+" ~ y) => x + y
        case (x, "-" ~ y) => x - y
      }
    }
  }
}