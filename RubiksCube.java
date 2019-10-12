/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author sophiasingh
 */
public class RubiksCube {

    private Face front;
    private Face left;
    private Face right;
    private Face up;
    private Face down;
    private Face back;
    private State state;
    private LinkedList<State> stateSequence;
    private HashMap<Colour, Colour> oppositeColour;
    private HashMap<FacePosition, Face> readFace;

    public RubiksCube()
    {
        this.front = new Face(Colour.WHITE, 3, 3);
        this.left = new Face(Colour.BLUE, 3, 3);
        this.right = new Face(Colour.GREEN, 3, 3);
        this.up = new Face(Colour.RED, 3, 3);
        this.down = new Face(Colour.ORANGE, 3, 3);
        this.back = new Face(Colour.YELLOW, 3, 3);
        this.state = State.SOLVED;

        this.stateSequence = new LinkedList<>();
        this.stateSequence.add(State.SHUFFLED);
        this.stateSequence.add(State.BOTTOMCROSS);
        this.stateSequence.add(State.FIRSTLAYER);
        this.stateSequence.add(State.SECONDLAYER);
        this.stateSequence.add(State.TOPCROSS);
        this.stateSequence.add(State.PERMUTEEDGE);
        this.stateSequence.add(State.PERMUTECORNER);
        this.stateSequence.add(State.THIRDLAYER);
        this.stateSequence.add(State.SOLVED);

        this.oppositeColour = new HashMap<>();
        this.oppositeColour.put(Colour.BLUE, Colour.GREEN);
        this.oppositeColour.put(Colour.GREEN, Colour.BLUE);
        this.oppositeColour.put(Colour.RED, Colour.ORANGE);
        this.oppositeColour.put(Colour.ORANGE, Colour.RED);
        this.oppositeColour.put(Colour.WHITE, Colour.YELLOW);
        this.oppositeColour.put(Colour.YELLOW, Colour.WHITE);

        this.readFace = new HashMap<>();
        this.readFace.put(FacePosition.UP, this.up);
        this.readFace.put(FacePosition.DOWN, this.down);
        this.readFace.put(FacePosition.RIGHT, this.right);
        this.readFace.put(FacePosition.LEFT, this.left);
        this.readFace.put(FacePosition.FRONT, this.front);
        this.readFace.put(FacePosition.BACK, this.back);

    }

    public RubiksCube(int x)
    {
        this.front = new Face(Colour.WHITE, x, x);
        this.left = new Face(Colour.ORANGE, x, x);
        this.right = new Face(Colour.RED, x, x);
        this.up = new Face(Colour.BLUE, x, x);
        this.down = new Face(Colour.GREEN, x, x);
        this.back = new Face(Colour.YELLOW, x, x);
        this.state = State.SOLVED;
    }

    public Face getFrontFace()
    {
        return this.front;
    }

    public Face getUpFace()
    {
        return this.up;
    }

    public Face getDownFace()
    {
        return this.down;
    }

    public Face getLeftFace()
    {
        return this.left;
    }

    public Face getRightFace()
    {
        return this.right;
    }

    public Face getBackFace()
    {
        return this.back;
    }

    public State getState()
    {
        return this.state;
    }

    public LinkedList<State> getStateSequence()
    {
        return this.stateSequence;
    }

    public HashMap<Colour, Colour> getOppositeColours()
    {
        return this.oppositeColour;
    }

    public void front()
    {
        this.front.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[this.up.getNumRow()-1][i];
        }

        Colour[] c2 = this.right.swapCol(c1, 0);
        Colour[] c3 = this.down.swapRowReverse(c2, 0);
        c1 = this.left.swapCol(c3, this.left.getNumCol()-1);
        this.up.swapRow(c1, this.up.getNumRow()-1);
    }

    public void frontInverse()
    {
        this.front.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumRow()];

        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[this.up.getNumRow()-1][i];
        }

        Colour[] c2 = this.left.swapCol(c1, this.left.getNumCol()-1);
        Colour[] c3 = this.down.swapRow(c2, 0);
        c1 = this.right.swapColReverse(c3, 0);
        this.up.swapRow(c1, this.up.getNumRow()-1);
    }

    public void up()
    {
        this.up.clockwiseTurn();
        Colour[] c1 = new Colour[this.front.getNumRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[0][i];
        }

        Colour[] c2 = this.left.swapRow(c1, 0);
        Colour[] c3 = this.back.swapRow(c2, 0);
        c1 = this.right.swapRow(c3, 0);
        this.front.swapRow(c1, 0);
    }

    public void upInverse()
    {
        this.up.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.front.getNumRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[0][i];
        }

        Colour[] c2 = this.right.swapRow(c1, 0);
        Colour[] c3 = this.back.swapRow(c2, 0);
        c1 = this.left.swapRow(c3, 0);
        this.front.swapRow(c1, 0);
    }

    public void down()
    {
        this.down.clockwiseTurn();
        Colour[] c1 = new Colour[this.front.getNumRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[this.front.getNumRow()-1][i];
        }

        Colour[] c2 = this.right.swapRow(c1, this.left.getNumRow()-1);
        Colour[] c3 = this.back.swapRow(c2, this.back.getNumRow()-1);
        c1 = this.left.swapRow(c3, this.right.getNumRow()-1);
        this.front.swapRow(c1, this.front.getNumRow()-1);
    }

    public void downInverse()
    {
        this.down.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.front.getNumRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[this.front.getNumRow()-1][i];
        }

        Colour[] c2 = this.left.swapRow(c1, this.left.getNumRow()-1);
        Colour[] c3 = this.back.swapRow(c2, this.back.getNumRow()-1);
        c1 = this.right.swapRow(c3, this.right.getNumRow()-1);
        this.front.swapRow(c1, this.front.getNumRow()-1);
    }

    public void left()
    {
        this.left.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumCol()];
        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[i][0];
        }

        Colour[] c2 = this.front.swapCol(c1, 0);
        Colour[] c3 = this.down.swapCol(c2, 0);
        c1 = this.back.swapColReverse(c3, this.back.getNumCol()-1);
        this.up.swapCol(c1, 0);
    }

    public void leftInverse()
    {
        this.left.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumCol()];
        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[i][0];
        }

        Colour[] c2 = this.back.swapCol(c1, 0);
        Colour[] c3 = this.down.swapCol(c2, 0);
        c1 = this.front.swapCol(c3, this.back.getNumCol()-1);
        this.up.swapCol(c1, 0);
    }

    public void right()
    {
        this.right.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumCol()];
        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[i][this.up.getNumCol()-1];
        }

        Colour[] c2 = this.back.swapCol(c1, 0);
        Colour[] c3 = this.down.swapColReverse(c2, this.down.getNumCol()-1);
        c1 = this.front.swapCol(c3, this.front.getNumCol()-1);
        this.up.swapCol(c1, this.up.getNumCol()-1);
    }

    public void rightInverse()
    {
        this.right.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumCol()];
        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[i][this.up.getNumCol()-1];
        }

        Colour[] c2 = this.front.swapCol(c1, this.front.getNumCol()-1);
        Colour[] c3 = this.down.swapCol(c2, this.down.getNumCol()-1);
        c1 = this.back.swapCol(c3, 0);
        this.up.swapCol(c1, this.up.getNumCol()-1);
    }

    public void back()
    {
        this.back.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[0][i];
        }

        Colour[] c2 = this.left.swapCol(c1, 0);
        Colour[] c3 = this.down.swapRowReverse(c2, this.down.getNumRow()-1);
        c1 = this.right.swapCol(c3, this.left.getNumCol()-1);
        this.up.swapRow(c1, 0);
    }

    public void backInverse()
    {
        this.back.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getNumRow()];

        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[0][i];
        }

        Colour[] c2 = this.right.swapCol(c1, this.left.getNumCol()-1);
        Colour[] c3 = this.down.swapRow(c2, this.down.getNumRow()-1);
        c1 = this.left.swapColReverse(c3, 0);
        this.up.swapRow(c1, this.up.getNumRow()-1);
    }

    public void XTurn()
    {
        Face save = this.front;
        this.front = this.down;
        this.down = this.back;
        this.back = this.up;
        this.up = save;
        this.right.clockwiseTurn();
        this.left.antiClockwiseTurn();
    }

    public void XInverseTurn()
    {
        Face save = this.front;
        this.front = this.up;
        this.up = this.back;
        this.back = this.down;
        this.down = save;
        this.right.clockwiseTurn();
        this.left.antiClockwiseTurn();
    }

    public void YTurn()
    {
        Face save = this.front;
        this.front = this.right;
        this.right = this.back;
        this.back = this.left;
        this.left = save;
        this.up.clockwiseTurn();
        this.down.antiClockwiseTurn();
    }

    public void YInverseTurn()
    {
        Face save = this.front;
        this.front = this.left;
        this.left = this.back;
        this.back = this.right;
        this.right = save;
        this.up.antiClockwiseTurn();
        this.down.clockwiseTurn();
    }

    public void ZTurn()
    {
        Face save = this.up;
        this.up = this.left;
        this.left = this.down;
        this.down = this.right;
        this.right = save;
        this.front.clockwiseTurn();
        this.back.antiClockwiseTurn();
    }


    public void ZInverseTurn()
    {
        Face save = this.up;
        this.up = this.right;
        this.right = this.down;
        this.down = this.left;
        this.left = save;
        this.front.antiClockwiseTurn();
        this.back.clockwiseTurn();
    }

    public void readCommand(Command com)
    {
        switch(com){
            case F:
            this.front();
            break;

            case B:
            this.back();
            break;

            case R:
            this.right();
            break;

            case L:
            this.left();
            break;

            case U:
            this.up();
            break;

            case D:
            this.down();
            break;

            case X:
            this.XTurn();
            break;

            case Y:
            this.YTurn();
            break;

            case Z:
            this.ZTurn();
            break;

            case Fi:
            this.frontInverse();
            break;

            case Bi:
            this.backInverse();
            break;

            case Ri:
            this.rightInverse();
            break;

            case Li:
            this.leftInverse();
            break;

            case Ui:
            this.upInverse();
            break;

            case Di:
            this.downInverse();
            break;

            case Xi:
            this.XInverseTurn();
            break;

            case Yi:
            this.YInverseTurn();
            break;

            case Zi:
            this.ZInverseTurn();
            break;

            case F2:
            this.front();
            this.front();
            break;

            case B2:
            this.back();
            this.back();
            break;

            case R2:
            this.right();
            this.right();
            break;

            case L2:
            this.left();
            this.left();
            break;

            case U2:
            this.up();
            this.up();
            break;

            case D2:
            this.down();
            this.down();
            break;

            case X2:
            this.XTurn();
            this.XTurn();
            break;

            case Y2:
            this.YTurn();
            this.YTurn();
            break;

            case Z2:
            this.ZTurn();
            this.ZTurn();
            break;

        }
    }

    public void shuffle()
    {
        Command[] commands = Command.values();
        Command newCommand;

        Command[] shuffle = new Command[3];
        int shuffleIndex = 0;

        for(int i = 0; i<100; i++) {
            do {
              newCommand = commands[(int)(Math.random()*commands.length)];
            } while (newCommand.equals(shuffle[0]) || newCommand.equals(shuffle[1]) || newCommand.equals(shuffle[2]));

            shuffle[shuffleIndex] = newCommand;
            shuffleIndex = (shuffleIndex++)%shuffle.length;
            this.readCommand(newCommand);
        }
        this.state = State.SHUFFLED;
    }

    public boolean scanCornerPermuted(Face up, Face right, Face front) {
        return (up.getColour() == up.getGrid()[up.getNumRow()-1][up.getNumCol()-1] ||
                up.getColour() == front.getGrid()[0][front.getNumCol()-1] ||
                up.getColour() == right.getGrid()[0][0]) &&
               (front.getColour() == up.getGrid()[up.getNumRow()-1][up.getNumCol()-1] ||
                front.getColour() == front.getGrid()[0][front.getNumCol()-1] ||
                front.getColour() == right.getGrid()[0][0]) &&
               (right.getColour() == up.getGrid()[up.getNumRow()-1][up.getNumCol()-1] ||
                right.getColour() == front.getGrid()[0][front.getNumCol()-1] ||
                right.getColour() == right.getGrid()[0][0]);
    }

    public boolean scanHorizontalLinePermuted() {
        return this.up.scanLeftEdgeOriented() && this.up.scanRightEdgeOriented() &&
               this.left.getColour() == this.oppositeColour.get(this.right.getColour()) &&
               this.right.getColour() == this.oppositeColour.get(this.left.getColour());
    }

    public boolean scanVerticalLinePermuted() {
        return this.up.scanTopEdgeOriented() && this.up.scanBottomEdgeOriented() &&
               this.front.getColour() == this.oppositeColour.get(this.back.getColour()) &&
               this.back.getColour() == this.oppositeColour.get(this.front.getColour());
    }

    public boolean scanCrossPermuted() {
        return this.scanHorizontalLinePermuted() && this.scanVerticalLinePermuted();
    }

    public boolean scanCornerOriented(Face up, Face front, Face right) {
        return (up.getColour() == up.getGrid()[up.getNumRow()-1][up.getNumCol()-1]) &&
               (front.getColour() == front.getGrid()[0][front.getNumCol()-1]) &&
               (right.getColour() == right.getGrid()[0][0]);
    }

    public boolean scanEdgeOriented(Face up, Face front) {
        return up.scanRowEdge(up.getColour(), up.getNumRow()-1) &&
               front.scanRowEdge(front.getColour(), 0);
    }

    public boolean scanCrossOriented() {
        return this.up.scanCross() &&
               this.scanEdgeOriented(this.up, this.front) &&
               this.scanEdgeOriented(this.up, this.back) &&
               this.scanEdgeOriented(this.up, this.left) &&
               this.scanEdgeOriented(this.up, this.right);
    }

    public boolean scanAllCornersOriented() {
        return this.scanCornerOriented(this.up, this.front, this.right) &&
               this.scanCornerOriented(this.up, this.left, this.front) &&
               this.scanCornerOriented(this.up, this.back, this.left) &&
               this.scanCornerOriented(this.up, this.right, this.back);
    }

    public FacePosition getFacePositionFromChar(char face) {
         switch(face){
            case 'F':
            return FacePosition.FRONT;

            case 'B':
            return FacePosition.BACK;

            case 'R':
            return FacePosition.RIGHT;

            case 'L':
            return FacePosition.LEFT;

            case 'U':
            return FacePosition.UP;

            case 'D':
            return FacePosition.DOWN;
        }
        return FacePosition.FRONT;
    }

    public FacePosition[] getFacesFromEdgePosition(EdgePosition edge) {
        FacePosition[] faces = new FacePosition[2];

        faces[0] = getFacePositionFromChar(edge.toString().charAt(0));
        faces[1] = getFacePositionFromChar(edge.toString().charAt(1));

        return faces;
    }

    public FacePosition[] getFacesFromCornerPosition(CornerPosition corner) {
        FacePosition[] faces = new FacePosition[3];

        faces[0] = getFacePositionFromChar(corner.toString().charAt(0));
        faces[1] = getFacePositionFromChar(corner.toString().charAt(1));
        faces[2] = getFacePositionFromChar(corner.toString().charAt(2));

        return faces;
    }

    public  ArrayList<EdgePosition> scanForEdges(Colour check) {
        ArrayList<EdgePosition> edges = new ArrayList<>();

        for(EdgePosition pos : EdgePosition.values()) {
            FacePosition[] faces = getFacesFromEdgePosition(pos);

            FacePosition face1 = faces[0];
            FacePosition face2 = faces[1];
            Face face = this.readFace.get(face1);

            switch(face1) {
                case FRONT: {
                    switch(face2) {
                        case UP:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case RIGHT:
                        if(face.scanColEdge(check, face.getNumCol()-1)) {
                            edges.add(pos);
                        }
                        break;
                        case LEFT:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case DOWN:
                        if(face.scanRowEdge(check, face.getNumRow()-1)) {
                            edges.add(pos);
                        }
                        break;
                    }
                    break;
                }
                case UP: {
                    switch(face2) {
                        case BACK:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case RIGHT:
                        if(face.scanColEdge(check, face.getNumCol()-1)) {
                            edges.add(pos);
                        }
                        break;
                        case LEFT:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case FRONT:
                        if(face.scanRowEdge(check, face.getNumRow()-1)) {
                            edges.add(pos);
                        }
                        break;
                    }
                    break;
                }
                case RIGHT: {
                    switch(face2) {
                        case UP:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case BACK:
                        if(face.scanColEdge(check, face.getNumCol()-1)) {
                            edges.add(pos);
                        }
                        break;
                        case FRONT:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case DOWN:
                        if(face.scanRowEdge(check, face.getNumRow()-1)) {
                            edges.add(pos);
                        }
                        break;
                    }
                    break;
                }
                case LEFT: {
                    switch(face2) {
                        case UP:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case FRONT:
                        if(face.scanColEdge(check, face.getNumCol()-1)) {
                            edges.add(pos);
                        }
                        break;
                        case BACK:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case DOWN:
                        if(face.scanRowEdge(check, face.getNumRow()-1)) {
                            edges.add(pos);
                        }
                        break;
                    }
                    break;
                }
                case DOWN: {
                    switch(face2) {
                        case FRONT:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case RIGHT:
                        if(face.scanColEdge(check, face.getNumCol()-1)) {
                            edges.add(pos);
                        }
                        break;
                        case LEFT:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case BACK:
                        if(face.scanRowEdge(check, face.getNumRow()-1)) {
                            edges.add(pos);
                        }
                        break;
                    }
                    break;
                }
                case BACK: {
                    switch(faces[1]) {
                        case UP:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case LEFT:
                        if(face.scanColEdge(check, face.getNumCol()-1)) {
                            edges.add(pos);
                        }
                        break;
                        case RIGHT:
                        if(face.scanRowEdge(check, 0)) {
                            edges.add(pos);
                        }
                        break;
                        case DOWN:
                        if(face.scanRowEdge(check, face.getNumRow()-1)) {
                            edges.add(pos);
                        }
                        break;
                    }
                    break;
                }
            }
        }

        return edges;
    }

    public CornerPosition[] scanForCorners(Colour check) {
        CornerPosition[] edges = new CornerPosition[4];

        for(CornerPosition pos : CornerPosition.values()) {
            FacePosition[] faces = getFacesFromCornerPosition(pos);

            FacePosition face1 = faces[0];
            FacePosition face2 = faces[1];
            FacePosition face3 = faces[3];
            Face face = this.readFace.get(face1);
            // check first face position, retrieve the correct corner from second, third position
        }

        return edges;
    }

    public String toString()
    {
        return "FRONT: " + this.front.toString() +
               "\n\nBACK: " + this.back.toString() +
               "\n\nRIGHT: " + this.right.toString() +
               "\n\nLEFT: " + this.left.toString() +
               "\n\nUP: " + this.up.toString() +
               "\n\nDOWN: " + this.down.toString();
    }
}
