package com.brubom.api.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.brubom.api.exception.DeserializationException;
import com.brubom.api.model.Transaction;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

@JsonComponent
public class TransactionDeserializer extends JsonDeserializer<Transaction> {
    @Override
    public Transaction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        try {

            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            TextNode timestamp = (TextNode) treeNode.get("timestamp");
            TextNode amount = (TextNode) treeNode.get("amount");

            Transaction transaction = new Transaction(new BigDecimal(amount.asText()),
                    Instant.parse(timestamp.asText()).toEpochMilli());

            return transaction;
        }catch (NullPointerException ex){
            throw new DeserializationException(DeserializationException.ErrorCodes.INVALID_JSON);
        }
        catch (Exception ex){
            throw new DeserializationException(DeserializationException.ErrorCodes.INVALID_FIELD_VALUE);
        }
    }
}
