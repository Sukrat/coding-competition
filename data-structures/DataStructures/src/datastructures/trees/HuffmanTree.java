package datastructures.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class HuffmanTree {

    public static class HuffmanNode {

        private int weight;
        private String letter;

        public HuffmanNode(int weight, String letter) {
            this.weight = weight;
            this.letter = letter;
        }

        public int getWeight() {
            return weight;
        }

        public String getLetter() {
            return letter;
        }

        @Override
        public String toString() {
            return letter != null ? letter : "";
        }
    }

    public static IBinNode<HuffmanNode> createHuffmanTree(List<HuffmanNode> list) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List size should be greater than zero!");
        }

        PriorityQueue<IBinNode<HuffmanNode>> priorityQueue = new PriorityQueue<>(
                (x, y) -> Integer.compare(x.element().getWeight(), y.element().getWeight()));
        List<LinkBinNode<HuffmanNode>> collect = list.stream()
                .map((x) -> new LinkBinNode<>(x))
                .collect(Collectors.toList());

        priorityQueue.addAll(collect);
        while (priorityQueue.size() > 1) {
            IBinNode<HuffmanNode> x = priorityQueue.remove();
            IBinNode<HuffmanNode> y = priorityQueue.remove();
            HuffmanNode totalHuffman = new HuffmanNode(x.element().getWeight() + y.element().getWeight(), null);
            IBinNode<HuffmanNode> node = new LinkBinNode<>(totalHuffman, x, y);
            priorityQueue.add(node);
        }
        return priorityQueue.remove();
    }

    public static Map<String, String> getCodeMap(IBinNode<HuffmanNode> root) {
        Map<String, String> map = new HashMap<>();
        Stack<String> codeStack = new Stack<>();
        Stack<IBinNode<HuffmanNode>> binaryStack = new Stack<>();

        codeStack.add("1");
        binaryStack.add(root.right());

        codeStack.add("0");
        binaryStack.add(root.left());

        while (!binaryStack.isEmpty()) {
            IBinNode<HuffmanNode> node = binaryStack.pop();
            String code = codeStack.pop();
            if (node.isLeaf()) {
                map.put(node.element().letter, code);
            } else {
                codeStack.add(code + "1");
                binaryStack.add(node.right());

                codeStack.add(code + "0");
                binaryStack.add(node.left());
            }
        }
        return map;
    }

}
