class ResistorColor {
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

  int colorCode([String color = 'black']) =>
      resistorCodes[color] ?? -1;

  List<String> get colors => resistorCodes.keys.toList();
}
