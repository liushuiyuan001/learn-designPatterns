import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Chapter13 {
	public static void main(String[] args) {
		int[] nums = {1,2};
		System.out.println(Arrays.toString(subsets(nums).toArray()));
	}
	
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new LinkedList<>();
		subsetsHelper(nums, 0 , new LinkedList<Integer>(), result);
		return result;
	}
	
	public static void subsetsHelper(int[] nums,int index,LinkedList<Integer> subsets, List<List<Integer>> result) {
		if (index == nums.length) {
			result.add(new LinkedList<>(subsets));
		} else if (index < nums.length) {
			subsetsHelper(nums, index + 1, subsets, result);
			subsets.add(nums[index]);
			subsetsHelper(nums, index + 1, subsets, result);
			subsets.removeLast();
		}
		
	}
}
