import java.util.ArrayList;
import java.util.*;

public class Game
{
  public Game ()
  {
  }

  public Game (int[]testingRolls)
  {
    _testingRolls = testingRolls;
  }

  private int Score;
  private int FirstBonusScore;
  private int SecondBonusScore;
  private Frame[] Frames = new Frame[10];

  private int[] _testingRolls = null;
  private int currentTestingRollIndex = 0;

  public void Start ()
  {
    java.text.SimpleDateFormat formatter =
      new java.text.SimpleDateFormat ("yyyy-MM-dd 'at' HH:mm:ss");
    Date date = new Date (System.currentTimeMillis ());
    System.out.println ("Starting Game " + formatter.format (date) + "...");

    Scanner sc = new Scanner (System.in);
    for (int i = 0; i < 10; i++)
      {
	System.out.println ("Frame Nb " + (i + 1) + ": ");
	Frame currentFrame = new Frame ();
	int firstRoll;
	do
	  {
	    System.out.println ("Enter first roll: ");
	    if (_testingRolls == null)
	      {
		while (!sc.hasNextInt ())
		  {
		    System.out.
		      println ("That's not a number! Enter new value:");
		    sc.next ();
		  }
		firstRoll = sc.nextInt ();
	      }
	    else
	      {
		firstRoll = _testingRolls[currentTestingRollIndex++];
	      }
	  }
	while (!currentFrame.TrySetFirstRoll (firstRoll));

	int secondRoll = -1;
	if (!currentFrame.IsStrike ())
	  {
	    do
	      {
		System.out.println ("Enter second roll: ");
		if (_testingRolls == null)
		  {
		    while (!sc.hasNextInt ())
		      {
			System.
			  out.println
			  ("That's not a number! Enter new value:");
			sc.next ();
		      }

		    secondRoll = sc.nextInt ();
		  }
		else
		  {
		    secondRoll = _testingRolls[currentTestingRollIndex++];
		  }
	      }
	    while (!currentFrame.TrySetSecondRoll (secondRoll));
	  }
	else
	  {
	    System.out.println ("It's a Strike!!");
	  }

	if (currentFrame.IsSpare ())
	  {
	    System.out.println ("It's a Spare!!");
	  }

	EvaluateNotReadyFramesScore (currentFrame, i);
	Frames[i] = currentFrame;

      }

    TryPlayBonusRolls ();

    System.out.println ("Total Score = " + Score);
  }

  public int GetScore ()
  {
    return Score;
  }

  private void
    EvaluateNotReadyFramesScore (Frame currentFrame, int currentFrameIndex)
  {
    Frame previousFrame = GetPreviousFrame (currentFrameIndex);
    Frame previousOfPreviousFrame =
      GetPreviousOfPreviousFrame (currentFrameIndex);

    currentFrame.CalculateScore ();
    if (currentFrame.IsScoreReady ())
      Score += currentFrame.GetScore ();

    if (previousOfPreviousFrame != null &&
	!previousOfPreviousFrame.IsScoreReady ())
      {
	if (previousOfPreviousFrame.IsStrike ())
	  {

	    previousOfPreviousFrame.AddScore (previousFrame.GetFirstRoll () +
					      previousFrame.GetSecondRoll ());

	    if (previousFrame.IsStrike ())
	      previousOfPreviousFrame.AddScore (currentFrame.GetFirstRoll ());
	    previousOfPreviousFrame.SetScoreReady ();

	    Score += previousOfPreviousFrame.GetScore ();
	  }

	if (previousOfPreviousFrame.IsSpare ())
	  {
	    previousOfPreviousFrame.AddScore (previousFrame.GetFirstRoll ());
	    previousOfPreviousFrame.SetScoreReady ();
	  }
      }

    if (previousFrame != null && !previousFrame.IsScoreReady ())
      {
	if (previousFrame.IsStrike () && !currentFrame.IsStrike ())
	  {
	    previousFrame.AddScore (currentFrame.GetFirstRoll () +
				    currentFrame.GetSecondRoll ());
	    previousFrame.SetScoreReady ();
	    Score += previousFrame.GetScore ();
	  }

	if (previousFrame.IsSpare ())
	  {
	    previousFrame.AddScore (currentFrame.GetFirstRoll ());
	    previousFrame.SetScoreReady ();
	    Score += previousFrame.GetScore ();
	  }
      }

  }

  private Frame GetPreviousFrame (int currentFrameIndex)
  {
    if (currentFrameIndex > 0)
      return Frames[currentFrameIndex - 1];
    return null;
  }

  private Frame GetPreviousOfPreviousFrame (int currentFrameIndex)
  {
    if (currentFrameIndex > 1)
      return Frames[currentFrameIndex - 2];
    return null;
  }

  private void TryPlayBonusRolls ()
  {
    Scanner sc = new Scanner (System.in);
    Frame lastFrame = Frames[9];
    if (lastFrame.IsScoreReady ())
      return;

    do
      {
	System.out.println ("Enter a bonus roll: ");

	if (_testingRolls == null)
	  {
	    while (!sc.hasNextInt ())
	      {
		System.out.println ("That's not a number! Enter new value:");
		sc.next ();
	      }
	    FirstBonusScore = sc.nextInt ();
	  }
	else
	  {
	    FirstBonusScore = _testingRolls[currentTestingRollIndex++];
	  }
      }
    while (FirstBonusScore > 10 || FirstBonusScore < 0);


    Frame beforeLastFrame = Frames[8];
    if (!beforeLastFrame.IsScoreReady ())
      {
	beforeLastFrame.AddScore (lastFrame.GetFirstRoll () +
				  FirstBonusScore);
	beforeLastFrame.SetScoreReady ();
	Score += beforeLastFrame.GetScore ();
      }

    if (lastFrame.IsStrike ())
      {
	do
	  {
	    System.out.println ("Enter a second bonus roll: ");
	    if (_testingRolls == null)
	      {
		while (!sc.hasNextInt ())
		  {
		    System.out.
		      println ("That's not a number! Enter new value:");
		    sc.next ();
		  }
		SecondBonusScore = sc.nextInt ();
	      }
	    else
	      {
		SecondBonusScore = _testingRolls[currentTestingRollIndex++];
	      }
	  }
	while (SecondBonusScore > 10 || SecondBonusScore < 0 || (FirstBonusScore < 10 && FirstBonusScore + SecondBonusScore > 10));

      }

    lastFrame.AddScore (FirstBonusScore + SecondBonusScore);
    lastFrame.SetScoreReady ();
    Score +=
      lastFrame.GetFirstRoll () + lastFrame.GetSecondRoll () +
      FirstBonusScore + SecondBonusScore;

  }

}
