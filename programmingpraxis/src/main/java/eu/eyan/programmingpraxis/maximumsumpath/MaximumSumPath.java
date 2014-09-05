package eu.eyan.programmingpraxis.maximumsumpath;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class MaximumSumPath
{

    private static int stepCounter;
    private static int sumCounter;

    private static int maximumSum(BinaryTreeNode node)
    {
        stepCounter++;
        if (node == null)
        {
            return 0;
        }
        int maxSumLeft = maximumSum(node.left);
        int maxSumRight = maximumSum(node.right);
        if (maxSumRight > maxSumLeft)
        {
            sumCounter++;
            return node.num + maxSumRight;
        }
        sumCounter++;
        return node.num + maxSumLeft;
    }

    private static List<Integer> maximumSumPath(BinaryTreeNode node)
    {
        stepCounter++;
        ArrayList<Integer> path = Lists.newArrayList();
        if (node == null)
        {
            return path;
        }
        path.add(node.num);
        List<Integer> maximumSumPathRight = maximumSumPath(node.right);
        List<Integer> maximumSumPathLeft = maximumSumPath(node.left);
        int maxSumLeft = sum(maximumSumPathLeft);
        int maxSumRight = sum(maximumSumPathRight);
        if (maxSumRight > maxSumLeft)
        {
            path.addAll(maximumSumPathRight);
        }
        else
        {
            path.addAll(maximumSumPathLeft);
        }
        return path;
    }

    private static int sum(List<Integer> nums)
    {
        int sum = 0;
        for (Integer num : nums)
        {
            sum += num;
            sumCounter++;
        }
        return sum;
    }

    @Before
    public void before()
    {
        stepCounter = 0;
        sumCounter = 0;
        System.out.print("Test: ");
    }

    @Test
    public void testMaximumSum() throws Exception
    {
        System.out.println("testMaximumSum");
        BinaryTreeNode root = createTestOverlappingBinaryTree();

        int maximumSum = maximumSum(root);

        assertThat(maximumSum).isEqualTo(23);
    }

    @Test
    public void testMaximumSumPath() throws Exception
    {
        System.out.println("testMaximumSumPath");
        BinaryTreeNode root = createTestOverlappingBinaryTree();

        List<Integer> maximumSumPath = maximumSumPath(root);

        assertThat(maximumSumPath).containsExactly(3, 7, 4, 9);
    }

    @After
    public void after()
    {
        System.out.println("stepCounter: " + stepCounter);
        System.out.println("sumCounter: " + sumCounter);
        System.out.println();
    }

    private BinaryTreeNode createTestOverlappingBinaryTree()
    {
        // 3
        // 7 4
        // 2 4 6
        // 8 5 9 3
        BinaryTreeNode leaf8 = new BinaryTreeNode(8);
        BinaryTreeNode leaf5 = new BinaryTreeNode(5);
        BinaryTreeNode leaf9 = new BinaryTreeNode(9);
        BinaryTreeNode leaf3 = new BinaryTreeNode(3);

        BinaryTreeNode node2 = new BinaryTreeNode(2, leaf8, leaf5);
        BinaryTreeNode node4 = new BinaryTreeNode(4, leaf5, leaf9);
        BinaryTreeNode node6 = new BinaryTreeNode(6, leaf9, leaf3);

        BinaryTreeNode node7 = new BinaryTreeNode(7, node2, node4);
        BinaryTreeNode node4_2 = new BinaryTreeNode(4, node4, node6);

        BinaryTreeNode root = new BinaryTreeNode(3, node7, node4_2);

        return root;
    }

    private static class BinaryTreeNode
    {

        private int num;
        private BinaryTreeNode left = null;
        private BinaryTreeNode right = null;

        public BinaryTreeNode(int num)
        {
            this.num = num;
        }

        public BinaryTreeNode(int num, BinaryTreeNode left, BinaryTreeNode right)
        {
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }
}