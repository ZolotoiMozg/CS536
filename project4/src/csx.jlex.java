/*  
 *  Updated by: Denzil Stefen Showers
 *  Student #9062589552
 *
 *  This is a JLex specification for a small subset of CSX tokens.
 *  Expand it to handle all CSX tokens as part of your solution for project 2 
 */
import java_cup.runtime.*;
class CSXToken {
	int linenum;
	int colnum;
	CSXToken(int line,int col){
		linenum=line;colnum=col;};
}
class CSXIntLitToken extends CSXToken {
	int intValue;
	CSXIntLitToken(int val,int line,int col){
		super(line,col);intValue=val;};
}
class CSXIdentifierToken extends CSXToken {
	String identifierText;
	CSXIdentifierToken(String text,int line,int col){
		super(line,col);identifierText=text;};
}
class CSXCharLitToken extends CSXToken {
	char charValue;
	CSXCharLitToken(char val,int line,int col){
		super(line,col);charValue=val;};
}
class CSXStringLitToken extends CSXToken {
	String stringText; // Full text of string literal,
                          //  including quotes & escapes
	CSXStringLitToken(String text,int line,int col){
		super(line,col);
		stringText=text;
	};
}
// This class is used to track line and column numbers
// Please feel free to change or extend it
class Pos {
	static int  linenum = 1; /* maintain this as line number current
                                 token was scanned on */
	static int  colnum = 1; /* maintain this as column number current
                                 token began at */
	static int  line = 1; /* maintain this as line number after
					scanning current token  */
	static int  col = 1; /* maintain this as column number after
					scanning current token  */
	static void setpos() { // set starting position for current token
		linenum = line;
		colnum = col;
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		3, 4, 5, 6, 7, 7, 8, 9,
		10, 11, 12, 13, 14, 15, 7, 16,
		17, 17, 17, 17, 17, 17, 17, 17,
		17, 17, 18, 19, 20, 21, 22, 7,
		7, 23, 24, 25, 26, 27, 28, 29,
		30, 31, 29, 32, 33, 29, 34, 35,
		36, 29, 37, 38, 39, 40, 41, 42,
		29, 29, 29, 43, 44, 45, 7, 7,
		7, 23, 24, 25, 26, 27, 28, 29,
		30, 31, 29, 32, 33, 29, 46, 35,
		36, 29, 37, 38, 47, 40, 41, 42,
		29, 29, 29, 48, 49, 50, 51, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 1, 2, 3, 1,
		1, 1, 4, 1, 5, 6, 7, 1,
		1, 8, 9, 10, 11, 1, 1, 1,
		1, 1, 1, 12, 1, 1, 1, 13,
		14, 1, 1, 1, 11, 1, 1, 1,
		11, 1, 1, 11, 11, 11, 11, 11,
		11, 11, 11, 11, 11, 11, 11, 11,
		11, 15, 16, 17, 18, 19, 20, 21,
		22, 23, 24, 1, 25, 12, 26, 27,
		28, 29, 30, 31, 32, 33, 34, 35,
		27, 36, 37, 38, 39, 40, 41, 42,
		43, 44, 45, 46, 47, 48, 49, 50,
		51, 52, 53, 54, 55, 56, 57, 58,
		59, 60, 61, 62, 63, 64, 65, 66,
		67, 68, 69, 70, 71, 72, 73, 74,
		75, 76, 77 
	};
	private int yy_nxt[][] = {
		{ 1, 2, 3, 4, 5, 6, 58, 1,
			62, 66, 7, 8, 9, 10, 11, 12,
			13, 14, 15, 16, 17, 18, 19, 20,
			105, 106, 20, 107, 119, 20, 20, 60,
			20, 20, 20, 20, 120, 108, 20, 109,
			20, 110, 121, 21, 1, 22, 20, 109,
			23, 70, 24, 74 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 25, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ 59, 59, -1, 6, 6, 26, 6, 6,
			6, 6, 6, 6, 6, 6, 6, 6,
			6, 6, 6, 6, 6, 6, 6, 6,
			6, 6, 6, 6, 6, 6, 6, 6,
			6, 6, 6, 6, 6, 6, 6, 6,
			6, 6, 6, 6, 63, 6, 6, 6,
			6, 6, 6, 6 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 29, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, 30,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			31, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 14, -1, -1, -1, -1, -1, 32,
			32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, -1, -1, -1, 32, 32,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 33, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 34, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 35, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ 69, 69, 69, 69, 69, 69, 73, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69 
		},
		{ 31, 31, 39, 31, 31, 31, 31, 31,
			31, 31, 31, 31, 31, 31, 31, 31,
			31, 31, 31, 31, 31, 31, 31, 31,
			31, 31, 31, 31, 31, 31, 31, 31,
			31, 31, 31, 31, 31, 31, 31, 31,
			31, 31, 31, 31, 31, 31, 31, 31,
			31, 31, 31, 31 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 32, -1, -1, -1, -1, -1, 32,
			32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, -1, -1, -1, 32, 32,
			-1, -1, -1, -1 
		},
		{ 76, 76, -1, 76, 76, 76, 76, 76,
			76, -1, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76 
		},
		{ -1, -1, -1, -1, -1, -1, 27, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ 59, 59, -1, 59, 59, 67, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 36, 20, 20, 20,
			20, 20, 64, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 64, 20,
			-1, -1, -1, -1 
		},
		{ 76, 76, -1, 76, 76, 76, 76, 76,
			76, 38, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			28, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ 59, 59, -1, 59, 59, 71, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 6, 59, 6, 6,
			59, 59, 59, 59 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 40,
			20, 20, 20, -1, -1, -1, 20, 40,
			-1, -1, -1, -1 
		},
		{ 76, 76, -1, 76, 76, 76, 76, 76,
			76, 78, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 61, 76, 61, 61,
			76, 76, 76, 76 
		},
		{ 57, 57, -1, 61, 61, 61, 61, 61,
			61, -1, 61, 61, 61, 61, 61, 61,
			61, 61, 61, 61, 61, 61, 61, 61,
			61, 61, 61, 61, 61, 61, 61, 61,
			61, 61, 61, 61, 61, 61, 61, 61,
			61, 61, 61, 61, 65, 61, 61, 61,
			61, 61, 61, 61 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 43, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 37, -1, -1 
		},
		{ -1, -1, -1, 80, 80, 26, 80, 80,
			80, 80, 80, 80, 80, 80, 80, 80,
			80, 80, 80, 80, 80, 80, 80, 80,
			80, 80, 80, 80, 80, 80, 80, 80,
			80, 80, 80, 80, 80, 80, 80, 80,
			80, 80, 80, 80, 82, 80, 80, 80,
			80, 80, 80, 80 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 44, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ 69, 69, 69, 69, 69, 69, 41, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69, 69, 69, 69, 69,
			69, 69, 69, 69 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 14, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 45, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ 76, 76, -1, 76, 76, 76, 76, 76,
			76, 42, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 46, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, 38, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 47, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 48, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, 80, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 80, -1, 80, 80,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			49, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 50, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 51,
			20, 20, 20, -1, -1, -1, 20, 51,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 52, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 53,
			20, 20, 20, -1, -1, -1, 20, 53,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 54, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 55, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 55, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 56, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 68, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 72,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 75, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 77,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 117,
			20, 20, 20, -1, -1, -1, 20, 117,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			79, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 81,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 83,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 84, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 85, 122,
			20, 20, 20, -1, -1, -1, 20, 122,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 86, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 87, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 87, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 88, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 89, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			90, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 91, 20, 111, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 92, 20,
			20, 112, 20, 113, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 93, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 94, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 95, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 96, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 97, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 98,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 99, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 99, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 100, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 101,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 102,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			103, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 104, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 104, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 114,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 115, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 116, 20,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		},
		{ -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1,
			-1, 20, -1, -1, -1, -1, -1, 20,
			20, 20, 20, 20, 20, 20, 20, 118,
			20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, -1, -1, -1, 20, 20,
			-1, -1, -1, -1 
		}
	};
	public Symbol yylex ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF, new  CSXToken(0,0));
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{//handle any remaining unmatched tokens
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid token(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -2:
						break;
					case 2:
						{Pos.col +=1;}
					case -3:
						break;
					case 3:
						{Pos.line +=1; Pos.col = 1;}
					case -4:
						break;
					case 4:
						{Pos.col +=1;}
					case -5:
						break;
					case 5:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.NOT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -6:
						break;
					case 6:
						{//handle other string defs (which will be invalid)
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("unclosed string(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -7:
						break;
					case 7:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.LPAREN,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -8:
						break;
					case 8:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.RPAREN,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -9:
						break;
					case 9:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.TIMES,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -10:
						break;
					case 10:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.PLUS,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -11:
						break;
					case 11:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.COMMA,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -12:
						break;
					case 12:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.MINUS,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -13:
						break;
					case 13:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.SLASH,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -14:
						break;
					case 14:
						{// positive or negative integers, with overflow/underflow check
		  				Pos.setpos(); Pos.col += yytext().length();
		  				//If begins with a "~" strip the "~", convert to a 
		  				//double and then negate the resulting value.
		  				String str = yytext();
		  				Double numeric;
		  				if (str.charAt(0) == '~'){
		  					str = str.substring(1);
		  					numeric = -Double.parseDouble(str);
		  				}
		  				else {numeric = Double.parseDouble(str);}
		  				//check for overflow
		  				if (numeric > Integer.MAX_VALUE){
		  					//print an error
		  					System.out.println(Pos.linenum + ":" + Pos.colnum
		  					+" \t**ERROR: Integer overflow (" + yytext() 
		  					+ " is too big)");
		  					//return MAX_VALUE integer
		  					return new Symbol(sym.INTLIT,new CSXIntLitToken(
							new Integer(Integer.MAX_VALUE),
							Pos.linenum,Pos.colnum));
		  				}
		  				//check for underflow
		  				else if (numeric < Integer.MIN_VALUE){
		  					//print an error
		  					System.out.println(Pos.linenum + ":" + Pos.colnum
		  					+" \t**ERROR: Integer underflow (" + yytext() 
		  					+ " is too small)");
		  					//return MIN_VALUE integer
		  					return new Symbol(sym.INTLIT,new CSXIntLitToken(
							new Integer(Integer.MIN_VALUE),
							Pos.linenum,Pos.colnum));
		  				}
		  				//otherwise just return the number with CSXIntLitToken
		 				else{
		 				return new Symbol(sym.INTLIT,
						new CSXIntLitToken(
							new Integer(numeric.intValue()),
		                		    	Pos.linenum,Pos.colnum));
		        		}
		        }
					case -15:
						break;
					case 15:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.COLON,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -16:
						break;
					case 16:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.SEMI,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -17:
						break;
					case 17:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.LT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -18:
						break;
					case 18:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.ASG,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -19:
						break;
					case 19:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.GT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -20:
						break;
					case 20:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -21:
						break;
					case 21:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.LBRACKET,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -22:
						break;
					case 22:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.RBRACKET,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -23:
						break;
					case 23:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.LBRACE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -24:
						break;
					case 24:
						{Pos.setpos(); Pos.col +=1;
		return new Symbol(sym.RBRACE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -25:
						break;
					case 25:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.NOTEQ,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -26:
						break;
					case 26:
						{//string literals
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.STRLIT,new CSXStringLitToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -27:
						break;
					case 27:
						{//handle runaway block comments
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("unclosed comment(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -28:
						break;
					case 28:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.CAND,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -29:
						break;
					case 29:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.INCREMENT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -30:
						break;
					case 30:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.DECREMENT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -31:
						break;
					case 31:
						{//single line comment to at EOF
						Pos.line+=1; Pos.col = 1;}
					case -32:
						break;
					case 32:
						{//handle invalid ids, starting with a digit
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid identifier(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -33:
						break;
					case 33:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.LEQ,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -34:
						break;
					case 34:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.EQ,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -35:
						break;
					case 35:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.GEQ,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -36:
						break;
					case 36:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.rw_IF,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -37:
						break;
					case 37:
						{Pos.setpos(); Pos.col +=2;
		return new Symbol(sym.COR,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -38:
						break;
					case 38:
						{//character literals
						Pos.setpos(); Pos.col += yytext().length();
						if (yytext().equals("'\\n'")){
						return new Symbol(sym.CHARLIT, new CSXCharLitToken(
						'\n',Pos.linenum,Pos.colnum));}
						else if (yytext().equals("'\\t'")){
						return new Symbol(sym.CHARLIT, new CSXCharLitToken(
						'\t',Pos.linenum,Pos.colnum));}
						else if (yytext().equals("'\\''")){
						return new Symbol(sym.CHARLIT, new CSXCharLitToken(
						'\'',Pos.linenum,Pos.colnum));}
						else if (yytext().equals("'\\\\'")){
						return new Symbol(sym.CHARLIT, new CSXCharLitToken(
						'\\',Pos.linenum,Pos.colnum));}
						else {
						return new Symbol(sym.CHARLIT,new CSXCharLitToken(
						yytext().charAt(1),Pos.linenum,Pos.colnum));}
						}
					case -39:
						break;
					case 39:
						{//single line comment
						Pos.line+=1; Pos.col = 1;}
					case -40:
						break;
					case 40:
						{Pos.setpos(); Pos.col +=3;
		return new Symbol(sym.rw_INT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -41:
						break;
					case 41:
						{//multi line comment
						//split yytext by new lines
						String[] str = yytext().split("\n");
						//increment line by number of newlines found
						Pos.line+=str.length-1; 
						//if >=1 newline, column is at length of last piece
						if (str.length>1){Pos.col=str[str.length-1].length()+1;}
						//else increment by length of yytext
						else{Pos.col+=yytext().length();}}
					case -42:
						break;
					case 42:
						{//handle invalid character literals
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid character(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -43:
						break;
					case 43:
						{Pos.setpos(); Pos.col +=4;
		return new Symbol(sym.rw_BOOL,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -44:
						break;
					case 44:
						{Pos.setpos(); Pos.col +=4;
		return new Symbol(sym.rw_CHAR,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -45:
						break;
					case 45:
						{Pos.setpos(); Pos.col +=4;
		return new Symbol(sym.rw_ELSE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -46:
						break;
					case 46:
						{Pos.setpos(); Pos.col +=4;
		return new Symbol(sym.rw_READ,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -47:
						break;
					case 47:
						{Pos.setpos(); Pos.col +=4;
		return new Symbol(sym.rw_TRUE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -48:
						break;
					case 48:
						{Pos.setpos(); Pos.col +=4;
		return new Symbol(sym.rw_VOID,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -49:
						break;
					case 49:
						{Pos.setpos(); Pos.col +=5;
		return new Symbol(sym.rw_BREAK,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -50:
						break;
					case 50:
						{Pos.setpos(); Pos.col +=5;
		return new Symbol(sym.rw_CLASS,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -51:
						break;
					case 51:
						{Pos.setpos(); Pos.col +=5;
		return new Symbol(sym.rw_CONST,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -52:
						break;
					case 52:
						{Pos.setpos(); Pos.col +=5;
		return new Symbol(sym.rw_FALSE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -53:
						break;
					case 53:
						{Pos.setpos(); Pos.col +=5;
		return new Symbol(sym.rw_PRINT,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -54:
						break;
					case 54:
						{Pos.setpos(); Pos.col +=5;
		return new Symbol(sym.rw_WHILE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -55:
						break;
					case 55:
						{Pos.setpos(); Pos.col +=6;
		return new Symbol(sym.rw_RETURN,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -56:
						break;
					case 56:
						{Pos.setpos(); Pos.col +=8;
		return new Symbol(sym.rw_CONTINUE,
			new CSXToken(Pos.linenum,Pos.colnum));}
					case -57:
						break;
					case 58:
						{//handle any remaining unmatched tokens
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid token(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -58:
						break;
					case 59:
						{//handle other string defs (which will be invalid)
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("unclosed string(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -59:
						break;
					case 60:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -60:
						break;
					case 62:
						{//handle any remaining unmatched tokens
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid token(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -61:
						break;
					case 63:
						{//handle other string defs (which will be invalid)
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("unclosed string(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -62:
						break;
					case 64:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -63:
						break;
					case 66:
						{//handle any remaining unmatched tokens
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid token(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -64:
						break;
					case 67:
						{//handle other string defs (which will be invalid)
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("unclosed string(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -65:
						break;
					case 68:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -66:
						break;
					case 70:
						{//handle any remaining unmatched tokens
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid token(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -67:
						break;
					case 71:
						{//handle other string defs (which will be invalid)
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("unclosed string(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -68:
						break;
					case 72:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -69:
						break;
					case 74:
						{//handle any remaining unmatched tokens
						Pos.setpos(); Pos.col += yytext().length();
						return new Symbol(sym.error,new CSXStringLitToken(
						new String("invalid token(" + yytext() + ")"),
						Pos.linenum,Pos.colnum));}
					case -70:
						break;
					case 75:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -71:
						break;
					case 77:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -72:
						break;
					case 79:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -73:
						break;
					case 81:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -74:
						break;
					case 83:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -75:
						break;
					case 84:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -76:
						break;
					case 85:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -77:
						break;
					case 86:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -78:
						break;
					case 87:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -79:
						break;
					case 88:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -80:
						break;
					case 89:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -81:
						break;
					case 90:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -82:
						break;
					case 91:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -83:
						break;
					case 92:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -84:
						break;
					case 93:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -85:
						break;
					case 94:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -86:
						break;
					case 95:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -87:
						break;
					case 96:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -88:
						break;
					case 97:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -89:
						break;
					case 98:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -90:
						break;
					case 99:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -91:
						break;
					case 100:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -92:
						break;
					case 101:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -93:
						break;
					case 102:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -94:
						break;
					case 103:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -95:
						break;
					case 104:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -96:
						break;
					case 105:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -97:
						break;
					case 106:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -98:
						break;
					case 107:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -99:
						break;
					case 108:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -100:
						break;
					case 109:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -101:
						break;
					case 110:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -102:
						break;
					case 111:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -103:
						break;
					case 112:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -104:
						break;
					case 113:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -105:
						break;
					case 114:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -106:
						break;
					case 115:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -107:
						break;
					case 116:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -108:
						break;
					case 117:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -109:
						break;
					case 118:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -110:
						break;
					case 119:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -111:
						break;
					case 120:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -112:
						break;
					case 121:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -113:
						break;
					case 122:
						{// one letter followed by letters or numbers or null
		   				Pos.setpos(); Pos.col += yytext().length();
		   				return new Symbol(sym.IDENTIFIER,new CSXIdentifierToken(
						new String(yytext()),Pos.linenum,Pos.colnum));}
					case -114:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
