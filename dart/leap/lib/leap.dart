class Leap {
  bool leapYear(int year) => divisibleBy(year, 4) && (!divisibleBy(year, 100) != divisibleBy(year, 400));

  static bool divisibleBy(int year, int divisor) => year % divisor == 0;
}
