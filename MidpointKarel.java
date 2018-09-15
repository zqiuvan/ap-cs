public class MidpointKarel extends SuperKarel
{
    
    /**
     * this program works for all worlds three units wide or larger,
     * and worlds 1 unit wide (but only because I specifically added
     * a section of code to account for that)
     * a 2 unit wide world is the single case that this program will
     * fail, though this can be amended with another small bit of
     * code
     */
    
    public void run()
    {
        // this little snippet is only for the case of 1 wide worlds
        if(frontIsBlocked())
        {
            putBall();
        }
        // this is the actual code
        else
        {
            markTwoEdges();
            /**
             * this while loop is reached every time Karel finishes
             * a markTwoEnds + moveInOne cycle
             */
            while(noBallsPresent())
            {
                /**
                 * this move + ballsPresent check is for Karel to
                 * determine whether she's done laying down the
                 * "calibration" balls
                 */
                move();
                /**
                 * if Karel moves onto a ball here, it means she was
                 * JUST standing on the centre tile
                 */
                if(ballsPresent())
                {
                    // turn around and go back to centre tile
                    turnAround();
                    move();
                    // finishing steps
                    markMiddle();
                    cleanUp();
                    goToMiddle();
                }
                else
                /**
                 * if Karel goes forward one and DOESN't step on a
                 * ball, it means she needs to do more markTwoEdges
                 * cycles
                 */
                {
                    /**
                     * Karel steps back to where she was before the
                     * check
                     */
                    turnAround();
                    move();
                    turnAround();
                    markTwoEnds();
                    moveInOne();
                }
            }
        }
        faceEast();
    }
    
    // duh
    
    private void faceEast()
    {
        while(notFacingEast())
        {
            turnLeft();
        }
    }
    
    /**
     * this method puts a ball at the leftmost and rightmost corner
     * of the world, setting Karel up to begin the markTwoEnds
     * cycle
     * preconditions: Karel is in the bottom left corner facing East
     * postconditions: balls placed and Karel is one from the right-
     * most corner, facing West
     */
    
    private void markTwoEdges()
    {
        putBall();
        while(frontIsClear())
        {
            move();
        }
        putBall();
        turnAround();
        move();
    }
    
    /**
     * this method makes Karel, well, go to the middle by detecting
     * the marking ball left behind after cleanUp
     * preconditions: markMiddle and cleanUp finished, so Karel
     * will be right against a wall facing it
     * postconditions: Karel is standing on the middle ball
     */
    
    private void goToMiddle()
    {
        turnAround();
        while(noBallsPresent())
        {
            move();
        }
    }
    
    /**
     * this method has Karel sweep the world to pick up the
     * buffering/calibration balls left behind from earlier
     * preconditions: markMiddle just finished - Karel is standing
     * in the middle of the world, on three balls
     * postconditions: world is empty save for Karel in the middle
     * standing on one ball
     */
    
    private void cleanUp()
    {
        while(frontIsClear())
        {
            if(ballsPresent())
            {
                takeBall();
            }
            move();
        }
        turnAround();
        while(frontIsClear())
        {
            if(ballsPresent())
            {
                takeBall();
            }
            move();
        }
        takeBall();
    }
    
    /**
     * marks middle of the world that Karel is standing on with
     * three balls
     */
    
    private void markMiddle()
    {
        for(int i = 0; i < 3; i++)
        {
            putBall();
        }
    }
    
    /**
     * this method marks the most "inwards" two ends that have not
     * yet been "buffered" by balls
     * preconditions: Karel is has a ball DIRECTLY behind her,
     * and the balls in the world are currently symmetrical in
     * postconditions: a new "layer" of symmetrical balls have been
     * added onto the existing "buffer", widening it by one, and
     * Karel is standing on the opposite side of where she began,
     * FACING where she began, on a ball
     */
     
    private void markTwoEnds()
    {
        putBall();
        move();
        while(noBallsPresent())
        {
            move();
        }
        turnAround();
    }
    
    /**
     * after markTwoEnds finishes, this method brings Karel "in"
     * one tile to assess whether she is done placing down
     * buffering/calibration balls, or whether she needs to begin
     * a new markTwoEnds cycle
     * preconditions: markTwoEnds just finished, and Karel is
     * standing on the ball that markTwoEnds has placed
     * postconditions: Karel has moved off the ball
     */
    
    private void moveInOne()
    {
        while(ballsPresent())
        {
            move();
        }
    }
}


