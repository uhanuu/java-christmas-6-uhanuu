package christmas.domain.view.input;

import camp.nextstep.edu.missionutils.Console;

public interface ConsoleInput {

    default String readLine() {
        return Console.readLine();
    }
}
