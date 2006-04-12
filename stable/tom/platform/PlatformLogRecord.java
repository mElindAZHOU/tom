/*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package tom.platform;

import java.util.logging.*;

public class PlatformLogRecord extends LogRecord {
  private int line;
  private String filePath;
  private PlatformMessage message;
  private boolean detailed;
  private int structDeclLine;
  private String structInfo;

  public PlatformLogRecord(Level level, PlatformMessage message, String filePath, int line) {
    this(level, message, new Object[]{} , filePath, line);
  }

  public PlatformLogRecord(Level level, PlatformMessage message, Object detail, String filePath, int line) {
    this(level, message, new Object[]{detail}, filePath, line);
  }

  public PlatformLogRecord(Level level, PlatformMessage message, Object[] detail, String filePath, int line) {
    super(level, message.getMessage());
    super.setParameters(detail);
    this.filePath =filePath;
    this.line = line;
    this.message = message;
    this.detailed=false;
  }

  public PlatformLogRecord(Level level, PlatformMessage message, Object[] detail, String filePath, int line,int structDeclLine,String structInfo) {
    super(level, message.getMessage());
    super.setParameters(detail);
    this.filePath =filePath;
    this.line = line;
    this.message = message;
    this.structDeclLine= structDeclLine;
    this.structInfo= structInfo;
    this.detailed = true;
  }


  public int getLine() {
    return line;
  }

  public int getStructDeclLine() {
    return structDeclLine;
  }

  public String getFilePath() {
    return filePath;
  }

  public boolean isDetailed(){
    return detailed;
  }
  public String getStructInfo() {
    return structInfo;
  }

  public PlatformMessage getPlatformMessage() {
    return message;
  }

} // class PlatformLogRecord
