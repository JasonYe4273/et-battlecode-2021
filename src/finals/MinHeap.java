package finals;

public class MinHeap {
  double [] keys;
  int [] newIndices; // newIndices[i] returns the new index of the object originally in index i
  int [] oldIndices; // oldIndices[i] returns the old index of the object currently in index i
  // note that newIndices[oldIndices[i]] = oldIndices[newIndices[i]] = i
  int n;
  public MinHeap (double [] keys) {
    n = keys.length;
    this.keys = keys.clone();
    newIndices = new int[n];
    oldIndices = new int[n];
    for (int i = 0; i < n; i ++) newIndices[i] = oldIndices[i] = i;
    for (int i = n/2 - 1; i >= 0; i--)
      minHeapify(i);
  }

  // swaps objects in indices i and j and updates the indices array accordingly
  public void swap (int i, int j) {
    // swap the objects
    double tmpKey = keys[i];
    keys[i] = keys[j];
    keys[j] = tmpKey;
    // swap the new indices at oldIndices[i] and oldIndices[j]
    newIndices[oldIndices[i]] = j;
    newIndices[oldIndices[j]] = i;
    // swap the old indices (which correspond to the original objects)
    int tmpInt = oldIndices[i];
    oldIndices[i] = oldIndices[j];
    oldIndices[j] = tmpInt;
  }

  // i is the index of a node in the heap
  // The children of i are 2i+1 and 2i+2
  // Assumption: Both children of i are roots of min heaps
  // Output: i will also be the root of a min heap
  public void minHeapify(int i) {
    if (2*i >= n-1) return; // i has no children
    else if (2*i == n-2) { // i has only a left child
      if (keys[i] > keys[2*i+1]) {
        swap(i, 2*i+1);
        minHeapify(2*i+1);
      }
    } else { // i has both left and right children
      if (keys[2*i+1] < keys[2*i+2]) {
        if (keys[i] > keys[2*i+1]) {
          swap(i, 2*i+1);
          minHeapify(2*i+1);
        }
      } else {
        if (keys[i] > keys[2*i+2]) {
          swap(i, 2*i+2);
          minHeapify(2*i+2);
        }
      }
    }
  }

  // return the old index of the minimum element in the heap
  public int extractMin() {
    if (n == 0) return -1;
    int minObject = oldIndices[0];
    swap(0, n-1);
    n--;
    minHeapify(0);
    return minObject;
  }

  // fix the heap after indices[originalIndex] has an updated key (smaller than the original)
  public void updateKey(int originalIndex, double newKey) {
    int i = newIndices[originalIndex];
    keys[i] = newKey;
    while (i > 0) {
      int parent = (i-1)/2;
      if (keys[i] < keys[parent]) swap(i, parent);
      i = parent;
    }
  }
}
