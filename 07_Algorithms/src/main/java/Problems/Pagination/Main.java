package Problems.Pagination;
// Servicio externo de paginacion que regresa la pagina que le pidas
// Problema, quieres llamar por cantidad de resultados, no por cantidad de paginas
// Debes de guardar los valores llamados en las instancias anteriores
// Debes de evitar hacer llamadas repetidas a la ultima pagina visitada

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Page {
    static final int PAGE_SIZE = 10;
    List<Integer> results = new ArrayList<>();
    int nextPageNumber = 0;
}
interface Client {
    Page fetch(int pageNumber);
}

class MockClient implements Client {

    private final int MAX_SIZE = 103;
    public final List<Integer> RESULTS = new ArrayList<>();

    public MockClient() {
        for (int i = 0; i <= MAX_SIZE; i++) {
            RESULTS.add(i);
        }
    }

    @Override
    public Page fetch(int pageNumber) {
        List<Page> pages = new ArrayList<>();
        int currentIndex = 0;

        if (pageNumber > 11) {
            return null;
        }

        for (int i = 0; i <= pageNumber; i++) {
            Page page = new Page();
            for (int j = 0; j < Page.PAGE_SIZE; j++) {
                if (currentIndex < MAX_SIZE) {
                    page.results.add(RESULTS.get(currentIndex));
                    currentIndex++;
                }
                else {
                    page.results.add(-1);
                }
            }
            if (page.results.get(Page.PAGE_SIZE - 1) == -1) {
                page.nextPageNumber = -1;
                return page;
            }

            page.nextPageNumber = i + 1;
            pages.add(page);
        }
        return pages.get(pageNumber);
    }
}

class Wrapper {
    private final Client client = new MockClient();
    private int currentIndex = -1;
    private int currentPageNum = -1;

    public List<Integer> fetchResults(int numberOfResults) {
        List<Integer> results = new ArrayList<>();
        int pageNum = this.currentPageNum == -1 ? 0: this.currentPageNum;

        while (results.size() < numberOfResults) {
            Page page = client.fetch(pageNum);
            int index = this.currentIndex == -1 ? 0: this.currentIndex;
            while (index < page.results.size() && results.size() < numberOfResults) {
                results.add(page.results.get(index));
                index++;
            }

            pageNum++;

            if (index < page.results.size()) {
                this.currentIndex = index;
                this.currentPageNum = pageNum - 1;
            } else {
                this.currentIndex = -1;
                this.currentPageNum = pageNum;
            }
        }

        return results;
    }
}


public class Main {
    public static void main(String[] args) {
        MockClient client = new MockClient();
        Wrapper wrapper = new Wrapper();

        testArrays(client.RESULTS.subList(0, 10), wrapper.fetchResults(10));
        testArrays(client.RESULTS.subList(10, 15), wrapper.fetchResults(5));
        testArrays(client.RESULTS.subList(15, 32), wrapper.fetchResults(17));
        testArrays(client.RESULTS.subList(32, 102), wrapper.fetchResults(70));
    }

    public static void testArrays(List<Integer> actual, List<Integer> expected) {
        if (actual.size() != expected.size()) {
            System.out.println(false);
            return;
        }

        for (int i = 0; i < actual.size(); i++) {
            if (!Objects.equals(actual.get(i), expected.get(i))) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }

}
