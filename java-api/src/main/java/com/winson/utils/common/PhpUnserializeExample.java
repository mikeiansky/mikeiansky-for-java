package com.winson.utils.common;

/**
 * @author mike ian
 * @date 2023/5/16
 * @desc
 **/
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhpUnserializeExample {

//    public static void main(String[] args) throws IOException {
////        ObjectMapper objectMapper = new ObjectMapper();
//        String phpSerializedData = "a:3:{i:0;s:5:\"apple\";i:1;s:6:\"orange\";i:2;s:6:\"banana\";}";
////        String phpSerializedData = "a:2:{s:4:\"name\";s:5:\"ciwei\";s:3:\"age\";i:7;}";
//
//        Object unserializedObject = unserialize(phpSerializedData, objectMapper);
//        System.out.println(unserializedObject);
//    }
//
//    public static Object unserialize(String serializedData, ObjectMapper objectMapper) throws IOException {
//        Object result;
//        int length = serializedData.length();
//
//        // Offset tracks the current position in the string.
//        int offset = 0;
//
//        if (length <= offset) {
//            throw new IllegalArgumentException("Empty serialized data");
//        }
//
//        switch (serializedData.charAt(offset)) {
//            // Boolean: b:([01]);
//            case 'b':
//                offset += 2;
//                boolean b = serializedData.charAt(offset++) == '1';
//                result = new Boolean(b);
//                break;
//
//            // Integer: i:([+-]?[0-9]+);
//            case 'i': {
//                int start = serializedData.indexOf(':', offset) + 1;
//                int end = serializedData.indexOf(';', start);
//                String number = serializedData.substring(start, end);
//
//                try {
//                    if (number.length() > 0 && number.charAt(0) == '+') {
//                        number = number.substring(1);
//                    }
//                    result = Long.valueOf(number);
//                } catch (NumberFormatException e) {
//                    throw new IllegalArgumentException("Invalid integer: " + number);
//                }
//
//                offset = end + 1;
//                break;
//            }
//
//            // Float/double: d:([+-]?[0-9]*\.?[0-9]+);
//            case 'd': {
//                int start = serializedData.indexOf(':', offset) + 1;
//                int end = serializedData.indexOf(';', start);
//                String number = serializedData.substring(start, end);
//
//                try {
//                    result = Double.valueOf(number);
//                } catch (NumberFormatException e) {
//                    throw new IllegalArgumentException("Invalid float: " + number);
//                }
//
//                offset = end + 1;
//                break;
//            }
//
//            // String: s:([0-9]+):"(.*?)";
//            case 's': {
//                int start = serializedData.indexOf(':', offset) + 1;
//                int lengthValue = Integer.parseInt(serializedData.substring(start, serializedData.indexOf(':', start + 1)));
//
//                // Skip past the quotes, plus the length specifier digits, plus the final semicolon
//                offset = start + Integer.toString(lengthValue).length() + 3;
//
//                result = serializedData.substring(offset, offset + lengthValue);
//
//                // Skip past the stringified value, plus the quotes and semicolon
//                offset += lengthValue + 2;
//                break;
//            }
//
//            // Array: a:([0-9]+):{(.*)}
//            case 'a': {
//                int start = serializedData.indexOf(':', offset) + 1;
//                int lengthValue = Integer.parseInt(serializedData.substring(start, serializedData.indexOf(':', start + 1)));
//
//                result = new HashMap<String, Object>();
//                offset = serializedData.indexOf('{', start) + 1;
//
//                for (int i = 0; i < lengthValue; i++) {
//                    // Get the key
//                    Object key = unserialize(serializedData.substring(offset), objectMapper);
//
//                    // Get the value
//                    offset += serializedData.substring(offset).indexOf(';') + 1;
//                    Object value = unserialize(serializedData.substring(offset), objectMapper);
//
//                    ((Map<String, Object>)result).put(key.toString(), value);
//
//                    offset += serializedData.substring(offset).indexOf(';') + 1;
//                }
//                offset += 1;
//                break;
//            }
//
//            // Object: O:([0-9]+):"(.*?)":([0-9]+):{(.*)}
//            case 'O': {
//                int start = serializedData.indexOf(':', offset) + 1;
//                int classNameLength = Integer.parseInt(serializedData.substring(start, serializedData.indexOf(':', start + 1)));
//                String className = serializedData.substring(serializedData.indexOf('"', start) + 1,
//                        serializedData.indexOf('"', start + classNameLength + 3));
//                offset += 2 + classNameLength;
//
//                int lengthValue = Integer.parseInt(serializedData.substring(serializedData.indexOf(':', offset) + 1,
//                        serializedData.indexOf(':', offset) + 2));
//                offset += 2;
//
//                result = new HashMap<String, Object>();
//                Map<String, Object> objectMap = (Map<String, Object>) result;
//                for (int i = 0; i < lengthValue; i++) {
//                    int endOfVariableName = serializedData.indexOf(':', offset);
//                    int startOfVariableLength = endOfVariableName + 2;
//                    int endOfVariableLength = serializedData.indexOf(':', startOfVariableLength);
//                    int variableLength = Integer.parseInt(serializedData.substring(startOfVariableLength, endOfVariableLength));
//                    String variableName = serializedData.substring(endOfVariableName + 1, startOfVariableLength - 2);
//
//                    offset = endOfVariableLength + 2;
//                    Object variableValue = unserialize(serializedData.substring(offset, offset + variableLength), objectMapper);
//                    offset += variableLength;
//                    objectMap.put(variableName, variableValue);
//                }
//
//                offset += 1;
//                break;
//            }
//
//            // Null: N;
//            case 'N':
//                result = null;
//                offset += 2;
//                break;
//
//            // Unknown/unsupported data
//            default:
//                throw new IllegalArgumentException("Invalid data found in serialized string");
//        }
//
//        return result;
//    }
}

