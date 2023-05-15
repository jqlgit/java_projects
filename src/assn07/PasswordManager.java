package assn07;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int hash_key = Math.abs(key.hashCode()) % 50;
        if (_passwords[hash_key] == null) {
            _passwords[hash_key] = new Account(key, value);
        }
        else {
            Account LL = _passwords[hash_key];
            while (LL.getNext() != null && !LL.getWebsite().equals(key)) {
                LL = LL.getNext();
            }
            if (LL.getWebsite().equals(key)){
                LL.setPassword(value);
            }
            else {
                LL.setNext(new Account(key, value));
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int hash_key = Math.abs(key.hashCode()) % 50;
        Account LL = _passwords[hash_key];
        if (_passwords[hash_key] == null) {
            return null;
        }
        while (LL.getNext() != null) {
            if (LL.getWebsite().equals(key)) {
                return (V) LL.getPassword();
            }
            LL = LL.getNext();
        }
        if (LL.getWebsite().equals(key)) {
            return (V) LL.getPassword();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int total = 0;
        for (int i = 0; i < 50; i++) {
            Account LL = _passwords[i];
            while (LL != null) {
                total++;
                if (LL.getNext() != null) {
                    LL = LL.getNext();
                }
                else {
                    LL = null;
                }
            }
        }
        return total;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        TreeSet<K> answer = new TreeSet<>();
        for (int i = 0; i < 50; i++) {
            Account LL = _passwords[i];
            while (LL != null) {
                answer.add((K) LL.getWebsite());
                LL = LL.getNext();
            }
        }
        return answer;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int hash_key = Math.abs(key.hashCode()) % 50;
        Account curr = _passwords[hash_key];
        Account prev = null;
        while (curr != null) {
            if (curr.getWebsite().equals(key)) {
                V returnPassword = (V) curr.getPassword();
                if (prev == null) {
                    _passwords[hash_key] = curr.getNext();
                }
                else {
                    prev.setNext(curr.getNext());
                }
                return returnPassword;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> answer = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Account LL = _passwords[i];
            while (LL != null) {
                if (LL.getPassword().equals(value)) {
                    answer.add((K) LL.getWebsite());
                }
                LL = LL.getNext();
            }
        }
        return answer;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}