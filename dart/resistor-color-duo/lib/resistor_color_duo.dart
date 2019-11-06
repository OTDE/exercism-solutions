import 'dart:math' as math;

class ResistorColorDuo {
  static const resistorCodes = {
    'black': 0,
    'brown': 1,
    'red': 2,
    'orange': 3,
    'yellow': 4,
    'green': 5,
    'blue': 6,
    'violet': 7,
    'grey': 8,
    'white': 9,
  };

  int value(List<String> colors) =>
      colors.reversed
          .toList()
          .asMap()
          .map<int, int>(
              (index, color) =>
                  MapEntry(index, resistorCodes[color] * math.pow(10, index).toInt()))
          .values
          .fold(0, (a, b) => a + b);

}
