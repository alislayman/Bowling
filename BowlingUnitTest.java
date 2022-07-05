public class BowlingUnitTest
{
  public static void Run ()
  {
    //// Edit test_AdditionalScenarioValid and test your scenario
    // boolean test_AdditionalScenarioValid = test_AdditionalScenario ();
    boolean test_NoSparesNoStrikesValid = test_NoSparesNoStrikes ();
    boolean test_ThreeConsecutiveStrikesValid =
      test_ThreeConsecutiveStrikes ();
    boolean test_allStrikesValid = test_allStrikes ();
    boolean test_allSparesValid = test_allSpares ();
    boolean test_LastTwoStrikesValid = test_LastTwoStrikes ();
    boolean test_LastTwoStrikeThenSpareValid = test_LastTwoStrikeThenSpare ();
    boolean test_LastTwoSpareThenStrikeValid = test_LastTwoSpareThenStrike ();

      System.out.println ("------ Unit Tests result ------");
    // System.out.println ("Additional Scenario: " + test_AdditionalScenarioValid);
      System.out.println ("No Spares No Strikes: " +
			  test_NoSparesNoStrikesValid);
      System.out.println ("Three Consecutive Strikes: " +
			  test_ThreeConsecutiveStrikesValid);
      System.out.println ("All Strikes: " + test_allStrikesValid);
      System.out.println ("All Spares: " + test_allSparesValid);
      System.out.println ("Last Two Strikes: " + test_LastTwoStrikesValid);
      System.out.println ("Last Two Strike Then Spare: " +
			  test_LastTwoStrikeThenSpareValid);
      System.out.println ("Last Two Spare Then Strike: " +
			  test_LastTwoSpareThenStrikeValid);
  }


  public static boolean test_AdditionalScenario ()
  {
    int expectedScore = -1;	// write the expected score here
    int rolls[] = { };		// write your rolls here

    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_NoSparesNoStrikes ()
  {
    int expectedScore = 70;
    int rolls[] =
      { 5, 3, 2, 4, 6, 1, 0, 0, 5, 3, 5, 2, 8, 0, 6, 2, 8, 1, 4, 5 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_ThreeConsecutiveStrikes ()
  {
    int expectedScore = 127;
    int rolls[] = { 5, 3, 2, 4, 10, 10, 10, 5, 2, 8, 0, 6, 2, 8, 1, 4, 5 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_LastIsStrike ()
  {
    int expectedScore = 70;
    int rolls[] =
      { 5, 3, 2, 4, 6, 1, 0, 0, 5, 3, 5, 2, 8, 0, 6, 2, 8, 1, 10, 5, 4 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_allStrikes ()
  {
    int expectedScore = 300;
    int rolls[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_allSpares ()
  {
    int expectedScore = 160;
    int rolls[] =
      { 5, 5, 4, 6, 7, 3, 3, 6, 7, 3, 9, 1, 9, 1, 5, 5, 8, 2, 6, 4, 10 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_LastTwoStrikes ()
  {
    int expectedScore = 97;
    int rolls[] =
      { 5, 3, 2, 4, 6, 1, 0, 0, 5, 3, 5, 2, 8, 0, 6, 2, 10, 10, 5, 5 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_LastTwoStrikeThenSpare ()
  {
    int expectedScore = 90;
    int rolls[] =
      { 5, 3, 2, 4, 6, 1, 0, 0, 5, 3, 5, 2, 8, 0, 6, 2, 10, 6, 4, 8 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }

  public static boolean test_LastTwoSpareThenStrike ()
  {
    int expectedScore = 92;
    int rolls[] =
      { 5, 3, 2, 4, 6, 1, 0, 0, 5, 3, 5, 2, 8, 0, 6, 2, 7, 3, 10, 7, 3 };
    Game game = new Game (rolls);
    game.Start ();
    int gameScore = game.GetScore ();
    return gameScore == expectedScore;
  }
}
