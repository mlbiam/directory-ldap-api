/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.directory.shared.util;


/**
 * Various character constants.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public interface CharConstants
{
    /** Hex chars */
    byte[] HEX_CHAR = new byte[]
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    int UTF8_MULTI_BYTES_MASK = 0x0080;
    int UTF8_TWO_BYTES_MASK = 0x00E0;
    int UTF8_TWO_BYTES = 0x00C0;
    int UTF8_THREE_BYTES_MASK = 0x00F0;
    int UTF8_THREE_BYTES = 0x00E0;
    int UTF8_FOUR_BYTES_MASK = 0x00F8;
    int UTF8_FOUR_BYTES = 0x00F0;
    int UTF8_FIVE_BYTES_MASK = 0x00FC;
    int UTF8_FIVE_BYTES = 0x00F8;
    int UTF8_SIX_BYTES_MASK = 0x00FE;
    int UTF8_SIX_BYTES = 0x00FC;

    /** &lt;alpha> ::= [0x41-0x5A] | [0x61-0x7A] */
    boolean[] ALPHA =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false
        };

    /** &lt;alpha-lower-case> ::= [0x61-0x7A] */
    boolean[] ALPHA_LOWER_CASE =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false
        };

    /** &lt;alpha-upper-case> ::= [0x41-0x5A] */
    boolean[] ALPHA_UPPER_CASE =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
        };

    /** &lt;alpha-digit> | &lt;digit> */
    boolean[] ALPHA_DIGIT =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false
        };

    /** &lt;alpha> | &lt;digit> | '-' */
    boolean[] CHAR =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, true,  false, false,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  false, false, false, false, false
        };

    /** %01-%27 %2B-%5B %5D-%7F */
    boolean[] UNICODE_SUBSET =
        {
            false, true,  true,  true,  true,  true,  true,  true, // '\0'
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            false, false, false, true,  true,  true,  true,  true, // '(', ')', '*'
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  false, true,  true,  true, // '\'
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  true,  true,  true,  true,  true,  true,
        };

    /** '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' */
    boolean[] DIGIT =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false
        };

    /** &lt;hex> ::= [0x30-0x39] | [0x41-0x46] | [0x61-0x66] */
    boolean[] HEX =
        {
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            true,  true,  true,  true,  true,  true,  true,  true,
            true,  true,  false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, true,  true,  true,  true,  true,  true,  false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false
        };

    /** A table containing booleans when the corresponding char is printable */
    boolean[] IS_PRINTABLE_CHAR =
        {
            false, false, false, false, false, false, false, false, // ---, ---, ---, ---, ---, ---, ---, ---
            false, false, false, false, false, false, false, false, // ---, ---, ---, ---, ---, ---, ---, ---
            false, false, false, false, false, false, false, false, // ---, ---, ---, ---, ---, ---, ---, ---
            false, false, false, false, false, false, false, false, // ---, ---, ---, ---, ---, ---, ---, ---
            true,  false, false, false, false, false, false, true,  // ' ', ---, ---, ---, ---, ---, ---, "'"
            true,  true,  false, true,  true,  true,  true,  true,  // '(', ')', ---, '+', ',', '-', '.', '/'
            true,  true,  true,  true,  true,  true,  true,  true,  // '0', '1', '2', '3', '4', '5', '6', '7',
            true,  true,  true,  false, false, true,  false, true,  // '8', '9', ':', ---, ---, '=', ---, '?'
            false, true,  true,  true,  true,  true,  true,  true,  // ---, 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            true,  true,  true,  true,  true,  true,  true,  true,  // 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'
            true,  true,  true,  true,  true,  true,  true,  true,  // 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W'
            true,  true,  true,  false, false, false, false, false, // 'X', 'Y', 'Z', ---, ---, ---, ---, ---
            false, true,  true,  true,  true,  true,  true,  true,  // ---, 'a', 'b', 'c', 'd', 'e', 'f', 'g'
            true,  true,  true,  true,  true,  true,  true,  true,  // 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'
            true,  true,  true,  true,  true,  true,  true,  true,  // 'p', 'q', 'r', 's', 't', 'u', 'v', 'w'
            true,  true,  true,  false, false, false, false, false  // 'x', 'y', 'z', ---, ---, ---, ---, ---
        };

    /** &lt;hex> ::= [0x30-0x39] | [0x41-0x46] | [0x61-0x66] */
    byte[] HEX_VALUE =
        {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00 -> 0F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10 -> 1F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 20 -> 2F
             0,  1,  2,  3,  4,  5,  6,  7,  8,  9, -1, -1, -1, -1, -1, -1, // 30 -> 3F ( 0, 1,2, 3, 4,5, 6, 7, 8, 9 )
            -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 40 -> 4F ( A, B, C, D, E, F )
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 50 -> 5F
            -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1  // 60 -> 6F ( a, b, c, d, e, f )
        };

    char[] TO_LOWER_CASE =
        {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F,
            0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17,
            0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F,
            ' ',  0x21, 0x22, 0x23, 0x24, 0x25, 0x26, '\'',
            '(',  ')',  0x2A, '+',  ',',  '-',  '.',  '/',
            '0',  '1',  '2',  '3',  '4',  '5',  '6',  '7',
            '8',  '9',  ':',  0x3B, 0x3C, '=',  0x3E, '?',
            0x40, 'a',  'b',  'c',  'd',  'e',  'f',  'g',
            'h',  'i',  'j',  'k',  'l',  'm',  'n',  'o',
            'p',  'q',  'r',  's',  't',  'u',  'v',  'w',
            'x',  'y',  'z',  0x5B, 0x5C, 0x5D, 0x5E, 0x5F,
            0x60, 'a',  'b',  'c',  'd',  'e',  'f',  'g',
            'h',  'i',  'j',  'k',  'l',  'm',  'n',  'o',
            'p',  'q',  'r',  's',  't',  'u',  'v',  'w',
            'x',  'y',  'z',  0x7B, 0x7C, 0x7D, 0x7E, 0x7F,
            0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
            0x88, 0x89, 0x8A, 0x8B, 0x8C, 0x8D, 0x8E, 0x8F,
            0x90, 0x91, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97,
            0x98, 0x99, 0x9A, 0x9B, 0x9C, 0x9D, 0x9E, 0x9F,
            0xA0, 0xA1, 0xA2, 0xA3, 0xA4, 0xA5, 0xA6, 0xA7,
            0xA8, 0xA9, 0xAA, 0xAB, 0xAC, 0xAD, 0xAE, 0xAF,
            0xB0, 0xB1, 0xB2, 0xB3, 0xB4, 0xB5, 0xB6, 0xB7,
            0xB8, 0xB9, 0xBA, 0xBB, 0xBC, 0xBD, 0xBE, 0xBF,
            0xC0, 0xC1, 0xC2, 0xC3, 0xC4, 0xC5, 0xC6, 0xC7,
            0xC8, 0xC9, 0xCA, 0xCB, 0xCC, 0xCD, 0xCE, 0xCF,
            0xD0, 0xD1, 0xD2, 0xD3, 0xD4, 0xD5, 0xD6, 0xD7,
            0xD8, 0xD9, 0xDA, 0xDB, 0xDC, 0xDD, 0xDE, 0xDF,
            0xE0, 0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xE6, 0xE7,
            0xE8, 0xE9, 0xEA, 0xEB, 0xEC, 0xED, 0xEE, 0xEF,
            0xF0, 0xF1, 0xF2, 0xF3, 0xF4, 0xF5, 0xF6, 0xF7,
            0xF8, 0xF9, 0xFA, 0xFB, 0xFC, 0xFD, 0xFE, 0xFF,
        };

    /** upperCase = 'A' .. 'Z', '0'..'9', '-' */
    char[] UPPER_CASE =
        {
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0, '-',   0,   0,
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9',   0,   0,   0,   0,   0,   0,
              0, 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z',   0,   0,   0,   0,   0,
              0, 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z',   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0,
              0,   0,   0,   0,   0,   0,   0,   0
        };

    int CHAR_ONE_BYTE_MASK = 0xFFFFFF80;
    int CHAR_TWO_BYTES_MASK = 0xFFFFF800;
    int CHAR_THREE_BYTES_MASK = 0xFFFF0000;
    int CHAR_FOUR_BYTES_MASK = 0xFFE00000;
    int CHAR_FIVE_BYTES_MASK = 0xFC000000;
    int CHAR_SIX_BYTES_MASK = 0x80000000;
    int NOT_EQUAL = -1;

    /**
     * The empty byte[]
     */
    byte[] EMPTY_BYTES = new byte[] {};
}
