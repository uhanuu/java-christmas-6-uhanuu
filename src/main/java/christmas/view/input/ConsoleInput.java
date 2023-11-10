package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;

public interface ConsoleInput {

    default String readLine() {
        return Console.readLine();
    }

    LocalDate requestVisitDate();
}
