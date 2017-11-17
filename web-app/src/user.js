exports.User = class User {
  constructor ({ userID, username, imageUrl, email, country, smashbit }) {
    this.userID = userID
    this.username = username
    this.imageUrl = imageUrl
    this.email = email
    this.country = country
    this.smashbit = smashbit
  }
}
