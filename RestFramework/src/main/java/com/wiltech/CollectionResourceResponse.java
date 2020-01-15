package com.wiltech;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dummy class to provide the name of the collection if one is not found.
 */
@Data
@Builder
@NoArgsConstructor
//@Relation(collectionRelation = "collection")
public class CollectionResourceResponse extends BaseDTO {

}
