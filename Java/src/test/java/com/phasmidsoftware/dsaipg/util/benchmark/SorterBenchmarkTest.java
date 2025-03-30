package com.phasmidsoftware.dsaipg.util.benchmark;

import com.phasmidsoftware.dsaipg.sort.linearithmic.MergeSort;
import com.phasmidsoftware.dsaipg.sort.helper.InstrumentedComparatorHelper;
import com.phasmidsoftware.dsaipg.util.config.Config;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;


public class SorterBenchmarkTest {

    @Test
    public void run() throws IOException {
        int[] sizes = {10000, 20000, 40000, 80000, 160000, 256000};

        for (int n : sizes) {
            String[] data = new String[n];
            for (int i = 0; i < n; i++) {
                data[i] = UUID.randomUUID().toString();
            }


            InstrumentedComparatorHelper<String> helper = new InstrumentedComparatorHelper<>("merge sort", String::compareTo, Config.load(getClass()));
            MergeSort<String> mergeSort = new MergeSort<>(helper);


            mergeSort.mutatingSort(data);


            System.out.printf("n = %d, compares = %d, copies = %d, hits = %d\n",
                    n,
                    helper.getCompares(),
                    helper.getCopies(),
                    helper.getHits());
        }
    }
}
