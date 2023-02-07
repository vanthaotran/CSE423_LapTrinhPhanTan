import java.util.*;
public class MsgList extends LinkedList {
    public void insert(SeqMessage sm) {
        int ts = sm.getSeqNo();
        ListIterator iter = super.listIterator(0);
        while (iter.hasNext()) {
            int t = ((SeqMessage) iter.next()).getSeqNo();
            if (ts <= t) break;
        }
        iter.add(sm); // error: add before
    }
    public Message removeM(int seqNo) {
        SeqMessage sm;
        ListIterator iter = super.listIterator(0);
        while (iter.hasNext()) {
            sm = (SeqMessage) iter.next();
            if (sm.getSeqNo() == seqNo) {
                iter.remove();
                return sm.getMessage();
            }
        }
        return null;
    }
}