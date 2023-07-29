/*
 * Copyright (C) 2021  Ricardo Villalobos Guevara - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package weliyek.amat.base;

import weliyek.amat.base.Direction;
import weliyek.amat.base.DataSegment;

public interface DeserializingSegment extends DataSegment
{

  @Override
  default Direction direction() {
    return Direction.DESERIALIZING;
  }

}
