package ua.university.HW_Task_10.HW;

public class Person {
    private String name;
    private String position;
    private double salary;
    private double monthlyAward;
    private double generalSum;

    public Person(String name, String position, double salary,
                  double monthlyAward, double generalSum) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.monthlyAward = monthlyAward;
        this.generalSum = generalSum;
    }

    @Override
    public String toString() {
        return  "\nname = \"" + name + "\"" +
                "\nposition = \"" + position + '\"' +
                "\nsalary = " + salary +
                "\nmonthlyAward = " + monthlyAward +
                "\ngeneralSum = " + generalSum;
    }

    public String getPersonProps() {
        return  name+";"+
                position+";"+
                salary+";"+
                monthlyAward+";"+
                generalSum;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public double getMonthlyAward() {
        return monthlyAward;
    }

    public double getGeneralSum() {
        return generalSum;
    }
}
