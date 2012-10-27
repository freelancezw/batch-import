package org.neo4j.batchimport;

import org.apache.log4j.Logger;
import org.neo4j.batchimport.structs.NodeStruct;
import org.neo4j.kernel.impl.nioneo.store.NodeRecord;
import org.neo4j.kernel.impl.nioneo.store.RelationshipRecord;

/**
 * @author mh
 * @since 27.10.12
 */
public class Utils {
    private final static Logger log = Logger.getLogger(Utils.class);

    public static int size(int[] ints) {
        if (ints==null) return 0;
        int count = ints.length;
        for (int i=count-1;i>=0;i--) {
            if (ints[i]!=-1) return i+1;
        }
        return count;
    }

    private static void printRelationship(RelationshipRecord record) {
        if (log.isDebugEnabled()) log.debug(formatRecord(record));
    }

    private static String formatRecord(RelationshipRecord record) {
        return String.format("Rel[%d] %s-[%d]->%s created %s chain start: %d->%d target %d->%d", record.getId(), record.getFirstNode(), record.getType(), record.getSecondNode(), record.isCreated(), record.getFirstPrevRel(), record.getFirstNextRel(), record.getSecondPrevRel(), record.getSecondNextRel());
    }

    private static void printNode(NodeStruct record) {
        if (log.isDebugEnabled()) log.debug(formatNode(record));
    }

    private static String formatNode(NodeStruct record) {
        return String.format("Node[%d] -> %d, .%d", record.id, record.nextRel, record.firstPropertyId);
    }

    private static String formatNode(NodeRecord record) {
        return String.format("Node[%d] -> %d, .%d", record.getId(), record.getNextRel(), record.getNextProp());
    }
}
