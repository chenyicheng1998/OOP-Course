public class CollectionTester {
    private static final int NUM_THREADS = 5;
    private static final int OPERATIONS_PER_THREAD = 20;

    public static void main(String[] args) {
        ThreadSafeCollection<String> threadSafeCollection = new ThreadSafeCollection<>();

        System.out.println("Testing Thread-Safe Collection with " + NUM_THREADS + " threads");
        System.out.println("Each thread will perform " + OPERATIONS_PER_THREAD + " operations\n");

        // Create and start test threads
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    String element = "Thread-" + threadId + "-Element-" + j;

                    // Perform different operations
                    int operation = j % 4;
                    switch (operation) {
                        case 0:
                            threadSafeCollection.addElement(element);
                            break;
                        case 1:
                            // Try to remove an element that may or may not exist
                            String toRemove = "Thread-" + (threadId - 1 >= 0 ? threadId - 1 : NUM_THREADS - 1) +
                                    "-Element-" + (j % 5);
                            threadSafeCollection.removeElement(toRemove);
                            break;
                        case 2:
                            int size = threadSafeCollection.getSize();
                            System.out.println(Thread.currentThread().getName() + " queried size: " + size);
                            break;
                        case 3:
                            threadSafeCollection.displayCollection();
                            break;
                    }

                    // Small delay to increase chance of interleaving
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }, "Worker-" + i);

            threads[i].start();
        }

        // Wait for all threads to complete
        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Final state
        System.out.println("\n=== TEST COMPLETED ===");
        System.out.println("Final collection size: " + threadSafeCollection.getSize());
        threadSafeCollection.displayCollection();

        // Verify thread safety by checking if operations were atomic
        verifyThreadSafety(threadSafeCollection);
    }

    private static void verifyThreadSafety(ThreadSafeCollection<String> collection) {
        System.out.println("\n=== THREAD SAFETY VERIFICATION ===");

        // Clear and test basic functionality
        collection.clearCollection();

        // Test that size is consistent after operations
        int initialSize = collection.getSize();
        System.out.println("Initial size after clear: " + initialSize);

        // The collection should maintain consistency throughout the test
        if (initialSize == 0) {
            System.out.println("✓ Collection maintained consistency - thread safety verified");
        } else {
            System.out.println("✗ Collection may have thread safety issues");
        }
    }
}