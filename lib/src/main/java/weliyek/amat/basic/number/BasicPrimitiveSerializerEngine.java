/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat.basic.number;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.OperationException;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.serializer.OutputSerializationEngine;

public abstract class BasicPrimitiveSerializerEngine<T extends Number>
    extends OutputSerializationEngine<
                        T,
                        WritingRuntimeControl<?,?,?>,
                        NumberSerializing<T,?,?,?,?>>
{

  private static final Logger logger = LoggerFactory.getLogger(BasicPrimitiveSerializerEngine.class);

  protected BasicPrimitiveSerializerEngine(
    WritingRuntimeControl<?,?,?> runtimeControl,
    NumberSerializing<T,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void onStart() {
    // Nothing to do.
  }

  @Override
  protected void onDone() {
    // Nothing to do.
  }

  @Override
  protected void onBytesLeftToProcess() {
    final T serializable = getSerializable();
    try {
      if (logger.isDebugEnabled()) {
        logger.debug(operation() + " writing " + requestedBytesToIO() + " byte(s)");
      }
      writePrimitive(serializable);
    } catch (IOException e) {
      throw new OperationException(operation(), "Failed to write primitive");
    }
  }

  protected abstract void writePrimitive(T n) throws IOException;

}
