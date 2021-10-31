import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SortUtils {

    public static void main(String[] args) {
        int[] arr = new int[]{4,2,34,9,2,3,1,6,8,1};

        System.out.println(simpleMergeSort(Arrays.stream(arr).boxed().toList()).stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" "))
        );
        int[] arr1 = new int[]{4,2,34,9,2,3,1,6,8,1};
        System.out.println(Arrays.stream(niceMergeSort(arr1)).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
        int[] arr2 = new int[]{4,2,34,9,2,3,1,6,8,1};
        System.out.println(Arrays.stream(theBestMergeSort(arr2)).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
    }

    public static int[] theBestMergeSort(int[] arr) {
        return _theBestMergeSort(arr, new int[arr.length], 0, arr.length-1);
    }

    private static int[] _theBestMergeSort(int[] arr, int[] buff, int s, int e) {
        if (s == e) {
            buff[s] = arr[s];
            return buff;
        }

        int middle = (s + e) / 2;

        int[] a1 = _theBestMergeSort(arr, buff, s, middle);
        int[] a2 = _theBestMergeSort(arr, buff, middle + 1, e);

        int[] target = a1 == arr ? buff : arr;

        int cur1 = s;
        int cur2 = middle + 1;

        for (int i = s; i <= e; i++) {
            if (cur1 <= middle && cur2 <= e) {
                if (a1[cur1] < a2[cur2]) {
                    target[i] = a1[cur1];
                    cur1++;
                } else {
                    target[i] = a2[cur2];
                    cur2++;
                }
            } else if (cur1 <= middle) {
                target[i] = a1[cur1];
                cur1++;
            } else {
                target[i] = a2[cur2];
                cur2++;
            }
        }
        return target;
    }

    private static int[] niceMergeSort(int[] arr) {
        _niceMergeSort(arr, new int[arr.length], 0, arr.length-1);
        return arr;
    }

    private static void _niceMergeSort(int[] arr, int[] buff, int start, int end) {
        if (start == end) {
            return;
        }

        int middle = (start + end) / 2;

        _niceMergeSort(arr, buff, start, middle);
        _niceMergeSort(arr, buff, middle+1, end);

        niceMerge(arr, buff, start, end);
    }

    private static void niceMerge(int[] arr, int[] buff, int start, int end) {
        int middle = (start + end) / 2;
        int cur1 = start;
        int cur2 = middle + 1;

        for (int i = start; i <= end; i++) {
            if (cur1 <= middle && cur2 <= end) {
                if (arr[cur1] < arr[cur2]) {
                    buff[i] = arr[cur1];
                    cur1++;
                } else {
                    buff[i] = arr[cur2];
                    cur2++;
                }
            } else if (cur1 <= middle) {
                buff[i] = arr[cur1];
                cur1++;
            } else {
                buff[i] = arr[cur2];
                cur2++;
            }
        }

        System.arraycopy(buff, start, arr, start, end - start + 1);
    }

    public static List<Integer> simpleMergeSort(List<Integer> arr) {
        if (arr.size() < 2) {
            return arr;
        }

        int middle = (arr.size() - 1) / 2;

        List<Integer> l1 = simpleMergeSort(arr.subList(0, middle + 1));
        List<Integer> l2 = simpleMergeSort(arr.subList(middle + 1, arr.size()));

        return simpleMerge(l1, l2);
    }


    public static List<Integer> simpleMerge(List<Integer> a1, List<Integer> a2) {
        List<Integer> result = new ArrayList<>(a1.size() + a2.size());

        int cur1 = 0;
        int cur2 = 0;
        for (int i = 0; i < a1.size() + a2.size(); i++) {
            if (cur1 < a1.size() && cur2 < a2.size()) {
                if (a1.get(cur1) < a2.get(cur2)) {
                    result.add(a1.get(cur1));
                    cur1++;
                } else {
                    result.add(a2.get(cur2));
                    cur2++;
                }
            } else if (cur1 < a1.size()) {
                result.add(a1.get(cur1));
                cur1++;
            } else {
                result.add(a2.get(cur2));
                cur2++;
            }
        }
        return result;
    }

}
