import java.util.*;

/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * Return the answer sorted by the frequency from highest to lowest.
 * Sort the words with the same frequency by their lexicographical order.

 * Example 1:
 *      Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 *      Output: ["i","love"]
 *      Explanation: "i" and "love" are the two most frequent words.
 *      Note that "i" comes before "love" due to a lower alphabetical order.

 * Example 2:
 *      Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 *      Output: ["the","is","sunny","day"]
 *      Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *      with the number of occurrence being 4, 3, 2 and 1 respectively.
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        // create a hashtable that maps words to their frequency
        Hashtable<String, Integer> map = new Hashtable<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        // create an array to denote the possible frequencies a word may occur in the input
        // the possible frequencies are: from 0 to words.length
        // element in the array is a list of all the words with frequency = index

        // for instance, given a map(String, frequency):
        // (fear = 1), (hatred = 1), (anger = 2), (leads = 2), (to = 2)
        // then the array would look like:
        // arr[1] = fear -> hatred
        // arr[2] = anger -> leads -> to
        // the other indexes are null

        LinkedList<String>[] frequencies = new LinkedList[words.length + 1];
        for (String keys : map.keySet()) {
            int index = map.get(keys);
            if (frequencies[index] == null) {
                frequencies[index] = new LinkedList<>();
            }
            frequencies[index].add(keys);
        }

        // sort the linked list in each array element
        for (LinkedList<String> freq : frequencies) {
            if (freq != null) {
                Collections.sort(freq);
            }
        }


        List<String> result = new LinkedList<>();

        // traverse the frequencies array backwards
        // (from most frequently occurring words to least)
        for (int i = words.length; i >= 0; i--) {
            if (frequencies[i] != null) {
                Iterator<String> iter = frequencies[i].listIterator();
                while (iter.hasNext() && result.size() < k) {
                    result.add(iter.next());
                    iter.remove();
                }
            }
            if (result.size() == k) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords freq = new TopKFrequentWords();

        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        List<String> result = freq.topKFrequent(words, 4);
        System.out.println(result);
    }
}
