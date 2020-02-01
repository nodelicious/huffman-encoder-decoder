package huffman_ed;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class huffmann_ED {

	public static void main(String[] args) {
		Node[] arr = new Node[10];
//		for (int i = 0; i < 10; i++) {
//			arr[i] = new Node("a", 100 - i, null, null);
//		}
//
//		Arrays.sort(arr);
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(arr[i].freq + " ");
//		}
		
		huffman_tree("sdhasjbdslfbhbakbkjbkjbhdksbkjsdbvhbvhkbvhbvhksdbjksbhkdbhsabkbhkbhkbdkjbcdskjfhcdjksbkjbcn");
		System.out.println(encode);
		System.out.println(encode("fsdfdfdfsfafads"));
		System.out.println(decode("1101001000001101000001101000001101001001101001100011010011000000100"));
	}

	public static class Node implements Comparable<Node> {
		String data;
		Node left = null;
		Node right = null;
		int freq = 0;

		Node(String data, int freq, Node left, Node right) {
			this.data = data;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		Node(int freq) {
			this.freq = freq;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.freq - o.freq;
		}
	}

	public static void huffman_tree(String str) {
		int[] freq = new int[26];
		for (int i = 0; i < str.length(); i++) {
			freq[str.charAt(i) - 'a']++;
		}

		PriorityQueue<Node> pr = new PriorityQueue<>();

		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > 0) {
				Node node = new Node((char) (i + 'a') + "", freq[i], null, null);
				pr.add(node);
			}

		}

		while (pr.size() != 1) {
			Node one = pr.remove();
			Node two = pr.remove();

			Node node = new Node(one.data + two.data, one.freq + two.freq, one, two);
			pr.add(node);
		}
		
		traversetree(pr.remove(), "");
	}

	static HashMap<String, String> decode = new HashMap<>();
	static HashMap<String, String> encode = new HashMap<>();

	public static void traversetree(Node node, String ans) {
		if (node.left == null && node.right == null) {
			decode.put(ans, node.data);
			encode.put(node.data, ans);
			return;
		}

		traversetree(node.left, ans + "0");
		traversetree(node.right, ans + "1");
	}

	public static String encode(String str) {

		StringBuilder sb=new StringBuilder();
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i);
			String s=ch+"";
			
			String res=encode.get(s);
			sb.append(res);
		}
		
		return sb.toString();
	}
	
	public static String decode(String str) {
		StringBuilder sb=new StringBuilder();
		int j=0;
		for(int i=1;i<=str.length();i++) {
			String possibleStr=str.substring(j,i);
			if(decode.containsKey(possibleStr)==true) {
				j=i;
				sb.append(decode.get(possibleStr));
			}
		}
		
		return sb.toString();
	}

}
