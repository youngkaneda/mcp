package ifpb.gpes.filter;

import ifpb.gpes.Call;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author juan
 */
public class FilterByMethod implements Predicate<Call> {

    private List<String> nameList;

    public FilterByMethod() {
        this.populateNameList();
    }

    private void populateNameList() {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("methods.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            this.nameList = reader.lines()
                    .map(s -> Arrays.asList(s.replace(" ", "").split(",")))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
    }

    @Override
    public boolean test(Call t) {
        return nameList.contains(t.getMethodName().split("\\[")[0]);
    }
}
