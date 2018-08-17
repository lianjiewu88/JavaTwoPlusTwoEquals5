package aop;

import java.lang.ref.PhantomReference;

import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

	public static void main(String[] args) {

Object phantomObj;

PhantomReference phantomRef, phantomRef2;

ReferenceQueue phantomQueue;

phantomObj = new String("Phantom Reference");

phantomQueue = new ReferenceQueue();

phantomRef = new PhantomReference(phantomObj, phantomQueue);

System.out.println("1 Phantom Reference:" + phantomRef.get());

System.out.println("2 Phantom Queued: " + phantomRef.isEnqueued());

phantomObj = null;

System.gc();

System.out.println("3 Anything in Queue? : " + phantomQueue.poll());

if (!phantomRef.isEnqueued()) {

System.out.println("4 Requestion finalization.");

System.runFinalization();

}

System.out.println("5 Anything in Queue?: " + phantomRef.isEnqueued());

phantomRef2 = (PhantomReference) phantomQueue.poll();

System.out.println("6 Original PhantomReference: " + phantomRef);

System.out.println("7 PhantomReference from Queue: " + phantomRef2);

}
}