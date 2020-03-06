package levels;

import collidable.DefaultBlockCreator;
import collidable.BlockCreator;
import collidable.DecoratorsFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tom Sendrovich.
 */
public class BlocksDefinitionReader {
    /**
     * BlocksFromSymbolsFactory - turns the block_definitions text to a Factory the creates
     * Blocks from specific symbols.
     * @param reader - Reader object.
     * @return BlocksFromSymbolsFactory object.
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        BlocksFromSymbolsFactory fromSymbolsFactory = new BlocksFromSymbolsFactory();
        DecoratorsFactory decoratorsFactory = new DecoratorsFactory();
        BufferedReader lineReader = new BufferedReader(reader);
        String line;
        Map<String, String> defaultMap = new HashMap<>();
        try {
            while ((line = lineReader.readLine()) != null) {
                line = line.trim();
                if ((!"".equals(line)) && (!line.startsWith("#"))) {
                    if (line.startsWith("default")) {
                        String str = line.substring("default".length()).trim();
                        defaultMap = extractParameter(str);
                    } else if (line.startsWith("bdef")) {
                        String str = line.substring("bdef".length()).trim();
                        Map<String, String> blockMap = extractParameter(str);
                        Map<String, String> combinedMaps = new HashMap(defaultMap);
                        combinedMaps.putAll(blockMap);
                        String symbol = extractSymbol(combinedMaps);
                        BlockCreator blockCreator = new DefaultBlockCreator();
                        for (String key : combinedMaps.keySet()) {
                            blockCreator = decoratorsFactory.decorate(blockCreator, key, combinedMaps.get(key));
                        }
                        fromSymbolsFactory.addBlockCreator(symbol, blockCreator);
                    } else if (line.startsWith("sdef")) {
                        String propertiesString = line.substring("sdef".length()).trim();
                        Map<String, String> spacesMap = extractParameter(propertiesString);


                        String symbol = extractSymbol(spacesMap);
                        int width = Integer.parseInt(spacesMap.get("width"));

                        fromSymbolsFactory.addSpacer(symbol, width);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not create a BlocksFromSymbolsFactory object");

        }
        return fromSymbolsFactory;
    }

    /**
     * extractSymbol.
     * @param map - a HashMap object.
     * @return a symbol.
     */
    private static String extractSymbol(Map<String, String> map) {
        String symbol = map.remove("symbol");

        if (symbol.length() != 1) {
            throw new RuntimeException("Symbol must be a single character: " + symbol);
        }
        return symbol.substring(0, 1);
    }

    /**
     * extractParameter - extract the symbol, block defintion and puts it in a map.
     * @param str - the extracted String object.
     * @return a map with symbols, and what to create from them.
     */
    private static Map<String, String> extractParameter(String str) {
        Map<String, String> map = new HashMap();
        String[] split = str.split(" ");
        for (String pair : split) {
            String[] split1 = pair.split(":");
            if (split1.length != 2) {
                throw new RuntimeException("Incorrect format : " + str);
            }
            map.put(split1[0], split1[1]);
        }
        return map;
    }


}
