package com.marrok.myapplication;
import com.google.gson.annotations.SerializedName;

public class Seller {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("identity_status")
    private boolean identityStatus;

    @SerializedName("identity_piece")
    private String identityPiece;

    public Seller(String username, String password, String email, String firstName, String lastName, String phoneNumber, boolean identityStatus, String identityPiece) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.identityStatus = identityStatus;
        this.identityPiece = identityPiece;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(boolean identityStatus) {
        this.identityStatus = identityStatus;
    }

    public String getIdentityPiece() {
        return identityPiece;
    }

    public void setIdentityPiece(String identityPiece) {
        this.identityPiece = identityPiece;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identityStatus=" + identityStatus +
                ", identityPiece='" + identityPiece + '\'' +
                '}';
    }
}
