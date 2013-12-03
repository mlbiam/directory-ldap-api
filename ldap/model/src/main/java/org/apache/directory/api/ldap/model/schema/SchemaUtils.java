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
package org.apache.directory.api.ldap.model.schema;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.directory.api.i18n.I18n;
import org.apache.directory.api.ldap.model.constants.MetaSchemaConstants;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.Modification;
import org.apache.directory.api.ldap.model.entry.Value;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.util.Strings;


/**
 * Various utility methods for schema functions and objects.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public final class SchemaUtils
{
    /**
     * Private constructor.
     */
    private SchemaUtils()
    {
    }


    /**
     * Gets the target entry as it would look after a modification operation
     * were performed on it.
     * 
     * @param mods the modifications performed on the entry
     * @param entry the source entry that is modified
     * @return the resultant entry after the modifications have taken place
     * @throws LdapException if there are problems accessing attributes
     */
    public static Entry getTargetEntry( List<? extends Modification> mods, Entry entry )
        throws LdapException
    {
        Entry targetEntry = entry.clone();

        for ( Modification mod : mods )
        {
            String id = mod.getAttribute().getId();

            switch ( mod.getOperation() )
            {
                case REPLACE_ATTRIBUTE:
                    targetEntry.put( mod.getAttribute() );
                    break;

                case ADD_ATTRIBUTE:
                    Attribute combined = mod.getAttribute().clone();
                    Attribute toBeAdded = mod.getAttribute();
                    Attribute existing = entry.get( id );

                    if ( existing != null )
                    {
                        for ( Value<?> value : existing )
                        {
                            combined.add( value );
                        }
                    }

                    for ( Value<?> value : toBeAdded )
                    {
                        combined.add( value );
                    }

                    targetEntry.put( combined );
                    break;

                case REMOVE_ATTRIBUTE:
                    Attribute toBeRemoved = mod.getAttribute();

                    if ( toBeRemoved.size() == 0 )
                    {
                        targetEntry.removeAttributes( id );
                    }
                    else
                    {
                        existing = targetEntry.get( id );

                        if ( existing != null )
                        {
                            for ( Value<?> value : toBeRemoved )
                            {
                                existing.remove( value );
                            }
                        }
                    }

                    break;

                default:
                    throw new IllegalStateException( I18n.err( I18n.ERR_04328, mod.getOperation() ) );
            }
        }

        return targetEntry;
    }


    // ------------------------------------------------------------------------
    // qdescrs rendering operations
    // ------------------------------------------------------------------------

    /**
     * Renders qdescrs into an existing buffer.
     * 
     * @param buf
     *            the string buffer to render the quoted description strs into
     * @param qdescrs
     *            the quoted description strings to render
     * @return the same string buffer that was given for call chaining
     */
    public static StringBuffer render( StringBuffer buf, List<String> qdescrs )
    {
        if ( ( qdescrs == null ) || ( qdescrs.size() == 0 ) )
        {
            return buf;
        }
        else if ( qdescrs.size() == 1 )
        {
            buf.append( "'" ).append( qdescrs.get( 0 ) ).append( "'" );
        }
        else
        {
            buf.append( "( " );

            for ( String qdescr : qdescrs )
            {
                buf.append( "'" ).append( qdescr ).append( "' " );
            }

            buf.append( ")" );
        }

        return buf;
    }


    /**
     * Renders qdescrs into a new buffer.<br>
     * <pre>
     * descrs ::= qdescr | '(' WSP qdescrlist WSP ')'
     * qdescrlist ::= [ qdescr ( SP qdescr )* ]
     * qdescr     ::= SQUOTE descr SQUOTE
     * </pre>
     * @param qdescrs the quoted description strings to render
     * @return the string buffer the qdescrs are rendered into
     */
    /* No qualifier */static StringBuffer renderQDescrs( StringBuffer buf, List<String> qdescrs )
    {
        if ( ( qdescrs == null ) || ( qdescrs.size() == 0 ) )
        {
            return buf;
        }

        if ( qdescrs.size() == 1 )
        {
            buf.append( '\'' ).append( qdescrs.get( 0 ) ).append( '\'' );
        }
        else
        {
            buf.append( "( " );

            for ( String qdescr : qdescrs )
            {
                buf.append( '\'' ).append( qdescr ).append( "' " );
            }

            buf.append( ")" );
        }

        return buf;
    }


    /**
     * Renders QDString into a new buffer.<br>
     * 
     * @param qdescrs the quoted description strings to render
     * @return the string buffer the qdescrs are rendered into
     */
    private static StringBuffer renderQDString( StringBuffer buf, String qdString )
    {
        buf.append( '\'' );

        for ( char c : qdString.toCharArray() )
        {
            switch ( c )
            {
                case 0x27:
                    buf.append( "\\27" );
                    break;

                case 0x5C:
                    buf.append( "\\5C" );
                    break;

                default:
                    buf.append( c );
                    break;
            }
        }

        buf.append( '\'' );

        return buf;
    }


    // ------------------------------------------------------------------------
    // objectClass list rendering operations
    // ------------------------------------------------------------------------

    /**
     * Renders a list of object classes for things like a list of superior
     * objectClasses using the ( oid $ oid ) format.
     * 
     * @param ocs
     *            the objectClasses to list
     * @return a buffer which contains the rendered list
     */
    public static StringBuffer render( ObjectClass[] ocs )
    {
        StringBuffer buf = new StringBuffer();

        return render( buf, ocs );
    }


    /**
     * Renders a list of object classes for things like a list of superior
     * objectClasses using the ( oid $ oid ) format into an existing buffer.
     * 
     * @param buf
     *            the string buffer to render the list of objectClasses into
     * @param ocs
     *            the objectClasses to list
     * @return a buffer which contains the rendered list
     */
    public static StringBuffer render( StringBuffer buf, ObjectClass[] ocs )
    {
        if ( ocs == null || ocs.length == 0 )
        {
            return buf;
        }
        else if ( ocs.length == 1 )
        {
            buf.append( ocs[0].getName() );
        }
        else
        {
            buf.append( "( " );

            for ( int ii = 0; ii < ocs.length; ii++ )
            {
                if ( ii + 1 < ocs.length )
                {
                    buf.append( ocs[ii].getName() ).append( " $ " );
                }
                else
                {
                    buf.append( ocs[ii].getName() );
                }
            }

            buf.append( " )" );
        }

        return buf;
    }


    // ------------------------------------------------------------------------
    // attributeType list rendering operations
    // ------------------------------------------------------------------------

    /**
     * Renders a list of attributeTypes for things like the must or may list of
     * objectClasses using the ( oid $ oid ) format.
     * 
     * @param ats
     *            the attributeTypes to list
     * @return a buffer which contains the rendered list
     */
    public static StringBuffer render( AttributeType[] ats )
    {
        StringBuffer buf = new StringBuffer();
        return render( buf, ats );
    }


    /**
     * Renders a list of attributeTypes for things like the must or may list of
     * objectClasses using the ( oid $ oid ) format into an existing buffer.
     * 
     * @param buf
     *            the string buffer to render the list of attributeTypes into
     * @param ats
     *            the attributeTypes to list
     * @return a buffer which contains the rendered list
     */
    public static StringBuffer render( StringBuffer buf, AttributeType[] ats )
    {
        if ( ats == null || ats.length == 0 )
        {
            return buf;
        }
        else if ( ats.length == 1 )
        {
            buf.append( ats[0].getName() );
        }
        else
        {
            buf.append( "( " );
            for ( int ii = 0; ii < ats.length; ii++ )
            {
                if ( ii + 1 < ats.length )
                {
                    buf.append( ats[ii].getName() ).append( " $ " );
                }
                else
                {
                    buf.append( ats[ii].getName() );
                }
            }
            buf.append( " )" );
        }

        return buf;
    }


    // ------------------------------------------------------------------------
    // schema object rendering operations
    // ------------------------------------------------------------------------


    /**
     * Renders the schema extensions into a new StringBuffer.
     *
     * @param extensions the schema extensions map with key and values
     * @return a StringBuffer with the extensions component of a syntax description
     */
    public static StringBuffer render( Map<String, List<String>> extensions )
    {
        StringBuffer buf = new StringBuffer();

        if ( extensions.isEmpty() )
        {
            return buf;
        }

        for ( Map.Entry<String, List<String>> entry : extensions.entrySet() )
        {
            buf.append( " " ).append( entry.getKey() ).append( " " );

            List<String> values = entry.getValue();

            // For extensions without values like X-IS-HUMAN-READIBLE
            if ( values == null || values.isEmpty() )
            {
                continue;
            }

            // For extensions with a single value we can use one qdstring like 'value'
            if ( values.size() == 1 )
            {
                buf.append( "'" ).append( values.get( 0 ) ).append( "' " );
                continue;
            }

            // For extensions with several values we have to surround whitespace
            // separated list of qdstrings like ( 'value0' 'value1' 'value2' )
            buf.append( "( " );
            for ( String value : values )
            {
                buf.append( "'" ).append( value ).append( "' " );
            }
            buf.append( ")" );
        }

        if ( buf.charAt( buf.length() - 1 ) != ' ' )
        {
            buf.append( " " );
        }

        return buf;
    }


    

    /**
     * Returns a String description of a schema. The resulting String format is :
     * <br>
     * (OID [DESC '<description>'] FQCN <fcqn> [BYTECODE <bytecode>] X-SCHEMA '<schema>')
     * <br>
     * @param description The description to transform to a String
     * @return
     */
    public static String render( LoadableSchemaObject description )
    {
        StringBuffer buf = new StringBuffer();
        buf.append( "( " ).append( description.getOid() );

        if ( description.getDescription() != null )
        {
            buf.append( " DESC " );
            renderQDString( buf, description.getDescription() );
        }

        buf.append( " FQCN " ).append( description.getFqcn() );

        if ( !Strings.isEmpty( description.getBytecode() ) )
        {
            buf.append( " BYTECODE " ).append( description.getBytecode() );
        }

        buf.append( " X-SCHEMA '" );
        buf.append( getSchemaName( description ) );
        buf.append( "' )" );

        return buf.toString();
    }


    private static String getSchemaName( SchemaObject desc )
    {
        List<String> values = desc.getExtensions().get( MetaSchemaConstants.X_SCHEMA_AT );

        if ( values == null || values.size() == 0 )
        {
            return MetaSchemaConstants.SCHEMA_OTHER;
        }

        return values.get( 0 );
    }


    /**
     * Remove the options from the attributeType, and returns the ID.
     * 
     * RFC 4512 :
     * attributedescription = attributetype options
     * attributetype = oid
     * options = *( SEMI option )
     * option = 1*keychar
     */
    public static String stripOptions( String attributeId )
    {
        int optionsPos = attributeId.indexOf( ';' );

        if ( optionsPos != -1 )
        {
            return attributeId.substring( 0, optionsPos );
        }
        else
        {
            return attributeId;
        }
    }


    /**
     * Get the options from the attributeType.
     * 
     * For instance, given :
     * jpegphoto;binary;lang=jp
     * 
     * your get back a set containing { "binary", "lang=jp" }
     */
    public static Set<String> getOptions( String attributeId )
    {
        int optionsPos = attributeId.indexOf( ';' );

        if ( optionsPos != -1 )
        {
            Set<String> options = new HashSet<String>();

            String[] res = attributeId.substring( optionsPos + 1 ).split( ";" );

            for ( String option : res )
            {
                if ( !Strings.isEmpty( option ) )
                {
                    options.add( option );
                }
            }

            return options;
        }
        else
        {
            return null;
        }
    }


    /**
     * Transform an UUID in a byte array
     * @param uuid The UUID to transform
     * @return The byte[] representing the UUID
     */
    public static byte[] uuidToBytes( UUID uuid )
    {
        Long low = uuid.getLeastSignificantBits();
        Long high = uuid.getMostSignificantBits();
        byte[] bytes = new byte[16];

        bytes[0] = ( byte ) ( ( high & 0xff00000000000000L ) >> 56 );
        bytes[1] = ( byte ) ( ( high & 0x00ff000000000000L ) >> 48 );
        bytes[2] = ( byte ) ( ( high & 0x0000ff0000000000L ) >> 40 );
        bytes[3] = ( byte ) ( ( high & 0x000000ff00000000L ) >> 32 );
        bytes[4] = ( byte ) ( ( high & 0x00000000ff000000L ) >> 24 );
        bytes[5] = ( byte ) ( ( high & 0x0000000000ff0000L ) >> 16 );
        bytes[6] = ( byte ) ( ( high & 0x000000000000ff00L ) >> 8 );
        bytes[7] = ( byte ) ( high & 0x00000000000000ffL );
        bytes[8] = ( byte ) ( ( low & 0xff00000000000000L ) >> 56 );
        bytes[9] = ( byte ) ( ( low & 0x00ff000000000000L ) >> 48 );
        bytes[10] = ( byte ) ( ( low & 0x0000ff0000000000L ) >> 40 );
        bytes[11] = ( byte ) ( ( low & 0x000000ff00000000L ) >> 32 );
        bytes[12] = ( byte ) ( ( low & 0x00000000ff000000L ) >> 24 );
        bytes[13] = ( byte ) ( ( low & 0x0000000000ff0000L ) >> 16 );
        bytes[14] = ( byte ) ( ( low & 0x000000000000ff00L ) >> 8 );
        bytes[15] = ( byte ) ( low & 0x00000000000000ffL );

        return bytes;
    }
}
