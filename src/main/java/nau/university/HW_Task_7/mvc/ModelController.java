package nau.university.HW_Task_7.mvc;

import java.util.Scanner;

public class ModelController {
    Scanner scanner = new Scanner(System.in);
    Randomizer model;
    ModelView modelView;
    int[] range = {0, 100};
    final int SCALING = 4;

    public boolean tt() {
        int tryNum;
        try {
            tryNum = inputNumber();
        } catch (Exception ex) {
            return false;
        }

        int randNum = model.rand(range[0], range[1]);
        modelView.printStatistic(range[0], range[1], randNum, tryNum);
        checkTryNum(tryNum);

        return isGameWinOrGamePass(randNum, tryNum);
    }

    public ModelController(Randomizer model, ModelView modelView) {
        this.model = model;
        this.modelView = modelView;
    }

    public void checkTryNum(int tryNum) {
        if (tryNum < range[1] - range[0]) {
            if (tryNum - range[0] > range[0])
                range[0] += (tryNum - range[0]) / SCALING;

        } else {
            range[1] -= tryNum + range[1];
            range[1] = Math.abs((range[1]) / SCALING);
        }
    }

    public int inputNumber() {
        System.out.print("Enter a number in range [" + range[0] + " " + range[1] + "]: ");
        return scanner.nextInt();
    }

    public boolean isGameWinOrGamePass(int randNum, int tryNum) {
        if (randNum == tryNum) {
            System.out.println("you win!!!");
            return false;
        }
        if (range[0] >= range[1]) {
            System.out.println("you lose!!!");
            return false;
        }

        return true;
    }
}
