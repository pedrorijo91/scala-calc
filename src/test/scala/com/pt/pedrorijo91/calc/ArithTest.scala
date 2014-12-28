package com.pt.pedrorijo91.calc

import org.scalatest.FunSuite

/**
 * Created by pedrorijo on 27/12/14.
 */
class ArithTest extends FunSuite {

  test("Parsing a single number should evaluate to the number itself") {
    assertResult(2.0)(Calc.parseAll(Calc.expr, "2").get)
  }

  test("Parsing a single negative number should evaluate to the number itself") {
    assertResult(-1.0)(Calc.parseAll(Calc.expr, "-1").get)
  }

  test("Operations should parse with negative numbers") {
    assertResult(1.0)(Calc.parseAll(Calc.expr, "2 + (-1)").get)
  }

  test("Parsing a single floating number should evaluate to the number itself") {
    assertResult(2.5)(Calc.parseAll(Calc.expr, "2.5").get)
  }

  test("Parsing a single number wrapped in parenthesis should evaluate to the number itself") {
    assertResult(2.0)(Calc.parseAll(Calc.expr, "(2.0)").get)
  }

  test("Parsing a sum of two numbers should evaluate to the sum of the numbers") {
    assertResult(3.0)(Calc.parseAll(Calc.expr, "2 + 1").get)
  }

  test("Parsing a subtraction of two numbers should evaluate to " +
    "the difference between the numbers") {
    assertResult(3.0)(Calc.parseAll(Calc.expr, "4 - 1").get)
  }

  test("Parsing a product of two numbers should evaluate to the product of the numbers") {
    assertResult(6.0)(Calc.parseAll(Calc.expr, "2 * 3").get)
  }

  test("Parsing a division of two numbers should evaluate to " +
    "the full division between the numbers") {
    assertResult(2.5)(Calc.parseAll(Calc.expr, "5 / 2").get)
  }

  test("Parsing should take into account operator precedence: " +
    "product/division over sum/subtraction") {
    assertResult(14.0)(Calc.parseAll(Calc.expr, " 2 + 3 * 4").get)
  }

  test("Parsing should take into account parenthesis precedence") {
    assertResult(20.0)(Calc.parseAll(Calc.expr, " (2 + 3) * 4").get)
  }

  test("Parsing with complex expression") {
    assertResult(50.0)(Calc.parseAll(Calc.expr, " (2 + 3) * 4 * 5 / (1.5 + 0.5)").get)
  }

  test("Parsing a power operation (a ** b) should evaluate to a powered to b") {
    assertResult(8.0)(Calc.parseAll(Calc.expr, "2**3").get)
  }

  test("Simple expression using power operation as a term") {
    assertResult(29.0)(Calc.parseAll(Calc.expr, "2 + (3 ** 3)").get)
  }

  test("Parsing should give higher precedence to power operation") {
    assertResult(29.0)(Calc.parseAll(Calc.expr, "2 + 3 ** 3").get)
  }
}
