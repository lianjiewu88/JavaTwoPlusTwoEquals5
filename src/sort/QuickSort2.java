package sort;

public class QuickSort2 {

	private int[] numbers;
	private int number;
	  
	public void sort(int[] values) {
	    if (values == null || values.length == 0){
	      return;
	    }
	    this.numbers = values;
	    number = values.length;
	    quicksort(0, number - 1);
	  }

	  private void quicksort(int low, int high) {
	    int i = low, j = high;
	    // 枢轴; 中心点，中枢; [物] 支点，支枢; [体] 回转运动;

	    int pivot = numbers[low + (high-low)/2];

	    while (i <= j) {
	      while (numbers[i] < pivot) {
	        i++;
	      }
	      while (numbers[j] > pivot) {
	        j--;
	      }

	      if (i <= j) {
	        swap(i, j);
	        i++;
	        j--;
	      }
	    }
	    if (low < j)
	      quicksort(low, j);
	    if (i < high)
	      quicksort(i, high);
	  }

	  private void swap(int i, int j) {
	    int temp = numbers[i];
	    numbers[i] = numbers[j];
	    numbers[j] = temp;
	  }
	  
	public static void main(String[] args) {
		int[] array = {1,3,2,4};
		QuickSort2 tool = new QuickSort2();
		tool.sort(array);
		for( int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
		
	}

}
