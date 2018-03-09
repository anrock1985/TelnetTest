package ru.twsecorp.telnetclient;

import java.io.*;
import java.net.Socket;

/*
RFC 854

THE NVT PRINTER AND KEYBOARD

      The NVT printer has an unspecified carriage width and page length
      and can produce representations of all 95 USASCII graphics (codes
      32 through 126).  Of the 33 USASCII control codes (0 through 31
      and 127), and the 128 uncovered codes (128 through 255), the
      following have specified meaning to the NVT printer:

         NAME                  CODE         MEANING

         NULL (NUL)              0      No Operation
         Line Feed (LF)         10      Moves the printer to the
                                        next print line, keeping the
                                        same horizontal position.
         Carriage Return (CR)   13      Moves the printer to the left
                                        margin of the current line.

         In addition, the following codes shall have defined, but not
         required, effects on the NVT printer.  Neither end of a TELNET
         connection may assume that the other party will take, or will
         have taken, any particular action upon receipt or transmission
         of these:

         BELL (BEL)              7      Produces an audible or
                                        visible signal (which does
                                        NOT move the print head).
         Back Space (BS)         8      Moves the print head one
                                        character position towards
                                        the left margin.
         Horizontal Tab (HT)     9      Moves the printer to the
                                        next horizontal tab stop.
                                        It remains unspecified how
                                        either party determines or
                                        establishes where such tab
                                        stops are located.
         Vertical Tab (VT)       11     Moves the printer to the
                                        next vertical tab stop.  It
                                        remains unspecified how
                                        either party determines or
                                        establishes where such tab
                                        stops are located.
         Form Feed (FF)          12     Moves the printer to the top
                                        of the next page, keeping
                                        the same horizontal position.
      All remaining codes do not cause the NVT printer to take any
      action.

      The sequence "CR LF", as defined, will cause the NVT to be
      positioned at the left margin of the next print line (as would,
      for example, the sequence "LF CR").  However, many systems and
      terminals do not treat CR and LF independently, and will have to
      go to some effort to simulate their effect.  (For example, some
      terminals do not have a CR independent of the LF, but on such
      terminals it may be possible to simulate a CR by backspacing.)
      Therefore, the sequence "CR LF" must be treated as a single "new
      line" character and used whenever their combined action is
      intended; the sequence "CR NUL" must be used where a carriage
      return alone is actually desired; and the CR character must be
      avoided in other contexts.  This rule gives assurance to systems
      which must decide whether to perform a "new line" function or a
      multiple-backspace that the TELNET stream contains a character
      following a CR that will allow a rational decision.

         Note that "CR LF" or "CR NUL" is required in both directions
         (in the default ASCII mode), to preserve the symmetry of the
         NVT model.  Even though it may be known in some situations
         (e.g., with remote echo and suppress go ahead options in
         effect) that characters are not being sent to an actual
         printer, nonetheless, for the sake of consistency, the protocol
         requires that a NUL be inserted following a CR not followed by
         a LF in the data stream.  The converse of this is that a NUL
         received in the data stream after a CR (in the absence of
         options negotiations which explicitly specify otherwise) should
         be stripped out prior to applying the NVT to local character
         set mapping.

      NAME               CODE              MEANING

      SE                  240    End of subnegotiation parameters.
      NOP                 241    No operation.
      Data Mark           242    The data stream portion of a Synch.
                                 This should always be accompanied
                                 by a TCP Urgent notification.
      Break               243    NVT character BRK.
      Interrupt Process   244    The function IP.
      Abort output        245    The function AO.
      Are You There       246    The function AYT.
      Erase character     247    The function EC.
      Erase Line          248    The function EL.
      Go ahead            249    The GA signal.
      SB                  250    Indicates that what follows is
                                 subnegotiation of the indicated
                                 option.
      WILL (option code)  251    Indicates the desire to begin
                                 performing, or confirmation that
                                 you are now performing, the
                                 indicated option.
      WON'T (option code) 252    Indicates the refusal to perform,
                                 or continue performing, the
                                 indicated option.
      DO (option code)    253    Indicates the request that the
                                 other party perform, or
                                 confirmation that you are expecting
                                 the other party to perform, the
                                 indicated option.
      DON'T (option code) 254    Indicates the demand that the
                                 other party stop performing,
                                 or confirmation that you are no
                                 longer expecting the other party
                                 to perform, the indicated option.
      IAC                 255    Data Byte 255.
 */

class TelnetClient {
    private Socket socket;
    private TelnetReader reader;
    private TelnetWriter writer;
    private TelnetDebug debug;

    TelnetClient(String ip) throws IOException {
        socket = new Socket(ip, 23);
        reader = new TelnetReader(socket);
        writer = new TelnetWriter(socket);
        debug = new TelnetDebug("c:\\Portable\\TelnetClient.log");
    }

    void dlinkLogIn() {
        if (reader.readUntil("tacacs+ login:")) {
            writer.write("a.pokazanov");
            if (reader.readUntil("password:")) {
                writer.write("celokoD)");
                read();
            }
        }
    }

    void test1() {
        System.out.println(reader.sbRead());
    }

    void test2() throws IOException {
        System.out.println(reader.sbReadX());
    }

    void testRaw() throws Exception {
        System.out.println(reader.sbReadSock());
    }

    void read() {
        try {
            reader.readChar(true, false, false);
        } catch (IOException e) {
            debug.log(e.getMessage());
        }
    }

    void write(String cmd) {
        writer.write(cmd);
    }

    boolean isConnected() {
        return socket.isConnected();
    }
}