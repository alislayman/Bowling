public class Frame
{
  private int FirstRoll;
  private int SecondRoll;
  private Boolean IsScoreReady;
  private int Score;

  public Boolean IsStrike ()
  {
    return FirstRoll == 10;
  }

  public Boolean IsSpare ()
  {
    return SecondRoll > 0 && (FirstRoll + SecondRoll) == 10;
  }

  public int GetFirstRoll ()
  {
    return FirstRoll;
  }

  public int GetSecondRoll ()
  {
    return SecondRoll;
  }

  public Boolean TrySetFirstRoll (int firstRollScore)
  {
    if (firstRollScore >= 0 && firstRollScore <= 10)
      {
	FirstRoll = firstRollScore;
	return true;
      }

    System.out.println ("Cannot set first roll: Invalid number");
    return false;
  }

  public Boolean TrySetSecondRoll (int secondRollScore)
  {
    if (IsStrike ())
      {
	System.out.println ("It's a strike. Second Roll not allowed!");
	return false;
      }

    if (secondRollScore >= 0 && secondRollScore <= (10 - FirstRoll))
      {
	SecondRoll = secondRollScore;
	return true;
      }

    System.out.println ("Cannot set second roll: Invalid number");
    return false;
  }

  public Boolean IsScoreReady ()
  {
    return IsScoreReady;
  }

  public void SetScoreReady ()
  {
    IsScoreReady = true;
  }

  public void CalculateScore ()
  {

    Score = FirstRoll + SecondRoll;
    IsScoreReady = !IsStrike () && !IsSpare ();
  }

  public void AddScore (int score)
  {
    Score += score;
  }
  
  public int GetScore()
  {
      return Score;
  }
}
