package Trees;


class TrieNode {
    boolean word;
    int counter;
    TrieNode[] child;


    public TrieNode() {
        counter = 0;
        word = false;
        child = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            child[i] = null;
        }
    }

    public void setWord(boolean word) {
        this.word = word;
    }

    public boolean isEnd() {
        return word;
    }

    public TrieNode get(char chr) {
        int range_pos = chr - 'a';
        return child[range_pos];
    }

    public void put(char chr, TrieNode node) {
        int range_pos = chr - 'a';
        child[range_pos] = node;
    }

    public boolean containsKey(char chr) {
        int range_pos = chr - 'a';
        return child[range_pos] != null;
    }


}

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public int insert(String word) {
        TrieNode current = root;
        int gcount = Integer.MAX_VALUE;
        int length = word.length();
        for (int x = 0; x < length; x++) {
            char chr = word.charAt(x);
            TrieNode node = current.get(chr);
            if (node == null) {
                node = new TrieNode();
                current.put(chr, node);
            } else {
                node.counter++;
                if (node.counter < gcount) {
                    gcount = node.counter;
                }
            }
            current = node;
        }
        current.setWord(true);
        if (gcount == Integer.MAX_VALUE) {
            return 0;
        }
        return gcount;
    }

    public TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int x = 0; x < prefix.length(); x++) {
            char chr = prefix.charAt(x);
            if (node.containsKey(chr)) {
                node = node.get(chr);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean searchWord(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("dog");
        trie.insert("racecar");
        int x = trie.insert("car");
        System.out.println(x);
    }
}
