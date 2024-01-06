package Laboratorium07;


import java.util.*;

class Question {
    private String content;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String content, List<String> options, int correctOptionIndex) {
        this.content = content;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getContent() {
        return content;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Test {
    private List<Question> questions;

    public Test() {
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
        }
    }
}

public class TestApplication2 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Test test = new Test();

        while (true) {
            System.out.println("1. Dodaj pytanie");
            System.out.println("2. Usuń pytanie");
            System.out.println("3. Przeprowadź test");
            System.out.println("4. Zakończ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addQuestion(test);
                    break;
                case 2:
                    removeQuestion(test);
                    break;
                case 3:
                    conductTest(test);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

    private static void addQuestion(Test test) {
        System.out.println("Podaj treść pytania:");
        String content = scanner.nextLine();

        System.out.println("Podaj odpowiedzi (oddzielone przecinkiem):");
        List<String> options = Arrays.asList(scanner.nextLine().split(", "));

        System.out.println("Podaj numer poprawnej odpowiedzi (1-" + options.size() + "):");
        int correctOptionIndex = scanner.nextInt();

        test.addQuestion(new Question(content, options, correctOptionIndex - 1));
        System.out.println("Dodano pytanie do testu.");
    }

    private static void removeQuestion(Test test) {
        System.out.println("Podaj numer pytania do usunięcia:");
        int index = scanner.nextInt();
        test.removeQuestion(index - 1);
        System.out.println("Usunięto pytanie z testu.");
    }

    private static void conductTest(Test test) {
        int correctAnswers = 0;

        for (int i = 0; i < test.getQuestions().size(); i++) {
            Question question = test.getQuestions().get(i);

            System.out.println((i + 1) + ". " + question.getContent());
            for (int j = 0; j < question.getOptions().size(); j++) {
                System.out.println("   " + (j + 1) + ". " + question.getOptions().get(j));
            }

            System.out.print("Podaj numer swojej odpowiedzi: ");
            int userAnswer = scanner.nextInt();

            if (userAnswer - 1 == question.getCorrectOptionIndex()) {
                correctAnswers++;
            }
        }

        double percentage = (double) correctAnswers / test.getQuestions().size() * 100;

        System.out.println("Wynik testu: " + correctAnswers + "/" + test.getQuestions().size() + " poprawnych odpowiedzi.");
        System.out.println("Ocena: " + getGrade(percentage));
    }

    private static String getGrade(double percentage) {
        if (percentage >= 90) {
            return "Bardzo dobra";
        } else if (percentage >= 79) {
            return "Plus dobra";
        } else if (percentage >= 68) {
            return "Dobra";
        } else if (percentage >= 57) {
            return "Plus dostateczna";
        } else {
            return "Niedostateczna";
        }
    }
}
